package ru.test.company.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
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
    public Employee createEmployee(EmployeeCreateArgument employeeCreateArgument) {
        Employee employee = Employee.builder()
                .firstName(employeeCreateArgument.getFirstName())
                .lastName(employeeCreateArgument.getLastName())
                .department(departmentService.getExisting(employeeCreateArgument.getDepartment_id()))
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getExisting(UUID employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    @Override
    public Employee updateEmployee(UUID uuid, EmployeeUpdateArgument employeeUpdateArgument) {
        Employee employee = Employee.builder()
                .firstName(employeeUpdateArgument.getFirstName())
                .lastName(employeeUpdateArgument.getLastName())
                .firstWorkingDate(employeeUpdateArgument.getFirstWorkingDate())
                .lastWorkingDate(employeeUpdateArgument.getLastWorkingDate())
                .department(departmentService.getExisting(employeeUpdateArgument.getDepartment_id()))
                .presenceAtWork(employeeUpdateArgument.getPresenceAtWork())
                .build();
        employee.setID(uuid);
        return employeeRepository.save(employee);
    }

    @Override
    public void removeEmployee(UUID employeeId) {
        employeeRepository.delete(employeeRepository.getOne(employeeId));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee dismissEmployee(UUID id) {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB.getLastWorkingDate() != null){
            System.out.println("Этот сотрудник уволен ранее");
        }else {
            Employee employee = Employee.builder()
                    .firstName(employeeFromDB.getFirstName())
                    .lastName(employeeFromDB.getLastName())
                    .firstWorkingDate(employeeFromDB.getFirstWorkingDate())
                    .lastWorkingDate(LocalDateTime.now())
                    .department(employeeFromDB.getDepartment())
                    .presenceAtWork(false)
                    .build();
            employee.setID(id);
            return employeeRepository.save(employee);
        }
            return employeeFromDB;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee setPresenceAtWorkEmployee(UUID id) {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB.getLastWorkingDate() != null){
            System.out.println("Этот сотрудник уволен ранее");
        }else {
            Employee employee = Employee.builder()
                    .firstName(employeeFromDB.getFirstName())
                    .lastName(employeeFromDB.getLastName())
                    .firstWorkingDate(employeeFromDB.getFirstWorkingDate())
                    .lastWorkingDate(LocalDateTime.now())
                    .department(employeeFromDB.getDepartment())
                    .presenceAtWork(false)
                    .build();
            employee.setID(id);
            return employeeRepository.save(employee);
        }
        return employeeFromDB;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee setAbsentedAtWorkEmployee(UUID id) {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB.getLastWorkingDate() != null){
            System.out.println("Этот сотрудник уволен ранее");
        }else {
            Employee employee = Employee.builder()
                    .firstName(employeeFromDB.getFirstName())
                    .lastName(employeeFromDB.getLastName())
                    .firstWorkingDate(employeeFromDB.getFirstWorkingDate())
                    .lastWorkingDate(LocalDateTime.now())
                    .department(employeeFromDB.getDepartment())
                    .presenceAtWork(false)
                    .build();
            employee.setID(id);
            return employeeRepository.save(employee);
        }
        return employeeFromDB;
    }

    @Override
    public Employee deleteEmployee(UUID id) {
        Employee employeeFromDB = getExisting(id);
        if(employeeFromDB == null){
            System.out.println("Не найден сотрудник!");
            return null;
        }
        if(employeeFromDB.getLastWorkingDate() == null){
            System.out.println("Этот сотрудник не уволен, сначала увольте его!");
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
        Employee employee = getExisting(uuid);

        return null;
    }




}
