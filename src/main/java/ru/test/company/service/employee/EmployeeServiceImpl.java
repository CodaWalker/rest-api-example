package ru.test.company.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.employee.EmployeeRepository;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Employee createEmployee(EmployeeCreateArgument argument) {
        Employee employee = Employee.builder()
                .firstName(argument.getFirstName())
                .lastName(argument.getLastName())
                .department(argument.getDepartment())
                .position(argument.getPosition())
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee getExisting(UUID employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    @Override
    @Transactional
    public Employee updateEmployee(UUID uuid, EmployeeUpdateArgument argument) {
        Employee employee = Employee.builder()
                .firstName(argument.getFirstName())
                .lastName(argument.getLastName())
                .firstWorkingDate(argument.getFirstWorkingDate())
                .lastWorkingDate(argument.getLastWorkingDate())
                .department(argument.getDepartment())
                .position(argument.getPosition())
                .presenceAtWork(argument.getPresenceAtWork())
                .build();
        employee.setID(uuid);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void removeEmployee(UUID employeeId) {
        employeeRepository.delete(employeeRepository.getOne(employeeId));
    }

    @Override
    @Transactional
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee dismissEmployee(UUID id) throws ErrorCustom {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB.getLastWorkingDate() != null){
            System.out.println("Этот сотрудник уволен ранее");
        }else {
            Employee employee = toCollectEmployee(id, false);
            return employeeRepository.save(employee);
        }
            return employeeFromDB;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee toCollectEmployee(UUID id,Boolean flag) throws ErrorCustom {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB == null){
            throw new ErrorCustom(3,"Не найден сотрудник");
        }
        if (employeeFromDB.getLastWorkingDate() != null) {
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        } else {
            Employee employee = Employee.builder()
                    .firstName(employeeFromDB.getFirstName())
                    .lastName(employeeFromDB.getLastName())
                    .firstWorkingDate(employeeFromDB.getFirstWorkingDate())
                    .lastWorkingDate(employeeFromDB.getLastWorkingDate())
                    .department(employeeFromDB.getDepartment())
                    .position(employeeFromDB.getPosition())
                    .presenceAtWork(flag)
                    .build();
            employee.setID(id);
            return employee;
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee setPresenceAtWorkEmployee(UUID id) throws ErrorCustom {
            Employee employee = toCollectEmployee(id,true);
            return employeeRepository.save(employee);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee setAbsentedAtWorkEmployee(UUID id) throws ErrorCustom {
        Employee employee = toCollectEmployee(id,false);
        return employeeRepository.save(employee);
        }

    @Override
    @Transactional
    public Integer getByEmployeesByEmployeeIdAndPositionId(UUID positionId, UUID departmentId) {
        return employeeRepository.countAllByPositionIdAndDepartmentIdAndPresenceAtWorkIsTrue(positionId, departmentId);
    }

    @Override
    @Transactional
    public Employee deleteEmployee(UUID id) throws ErrorCustom {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB == null){
            throw new ErrorCustom(3,"Не найден сотрудник");
        }
        if(employeeFromDB.getLastWorkingDate() == null){
            throw new ErrorCustom(2,"Этот сотрудник не уволен, необходимо предварительно его уволить");
        }else {
            employeeRepository.delete(employeeFromDB);
        }
        return employeeFromDB;
    }

    @Override
    public Long getCountDaysInCompany(UUID uuid) {
        Employee employee = getExisting(uuid);
        LocalDateTime localDateTime2 = employee.getFirstWorkingDate();
        if(employee.getLastWorkingDate() != null){
            return localDateTime2.until(employee.getLastWorkingDate(), ChronoUnit.DAYS);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime2.until(localDateTime, ChronoUnit.DAYS);
    }

    @Override
    public Long getCountWorkDaysInCompany(UUID uuid) {
        return null;
    }




}
