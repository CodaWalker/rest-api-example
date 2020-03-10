package ru.test.company.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.api.employee.dto.out.DepartmentReportDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.employee.EmployeeRepository;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.time.LocalDate;
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
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        }else {
            Employee employee = toCollectEmployee(id);
            return employeeRepository.save(employee);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Employee toCollectEmployee(UUID id) throws ErrorCustom {
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
                    .lastWorkingDate(LocalDateTime.now())
                    .department(employeeFromDB.getDepartment())
                    .position(employeeFromDB.getPosition())
                    .build();
            employee.setID(id);
            return employee;
        }
    }

    @Override
    @Transactional
    public Integer getByEmployeesByEmployeeIdAndPositionId(UUID positionId, UUID departmentId) {
        return employeeRepository.countAllByPositionIdAndDepartmentId(positionId, departmentId);
    }

    @Override
    @Transactional
    public Long getReportAllHolidayThisDay(UUID id) {
        return employeeRepository.countAllByDepartmentIdIncludeInterval(id, Event.ABSENTED_HOLIDAY,LocalDate.now(), LocalDate.now());
    }

    @Override
    @Transactional
    public Long getReportAllMedicalThisDay(UUID id) {
        return employeeRepository.countAllByDepartmentIdIncludeInterval(id, Event.ABSENTED_MEDICAL,LocalDate.now(), LocalDate.now());
    }

    @Override
    @Transactional
    public Long getReportAllAbsentedOtherThisDay(UUID id) {
        final Long otherEventThisDay = employeeRepository.countAllByDepartmentIdIncludeInterval(id, Event.ABSENTED_OTHER, LocalDate.now(), LocalDate.now());

        long eventsThisDay = getReportAllWorkingThisDay(id) +
                getReportAllMedicalThisDay(id) +
                getReportAllHolidayThisDay(id) +
                otherEventThisDay;

        if(countAllByDepartmentId(id) > eventsThisDay){
            return otherEventThisDay + ( countAllByDepartmentId(id) - eventsThisDay);
        }
        return otherEventThisDay;
    }

    @Override
    @Transactional
    public Long countAllByDepartmentId(UUID id) {
        return employeeRepository.countAllByDepartmentId(id);
    }

    @Override
    @Transactional
    public Long getReportAllWorkingThisDay(UUID id) {
        return employeeRepository.countAllByDepartmentIdIncludeInterval(id, Event.PRESENCE_AT_WORK,LocalDate.now(), LocalDate.now());
    }

    @Override
    public DepartmentReportDto getReportAll(UUID id) {
        return DepartmentReportDto.builder()
                .countAllEmployees(countAllByDepartmentId(id))
                .countAllWorkingEmployees(getReportAllWorkingThisDay(id))
                .countMedicalAbsentedEmployees(getReportAllMedicalThisDay(id))
                .countHolidayAbsentedEmployees(getReportAllHolidayThisDay(id))
                .countOtherAbsentedEmployees(getReportAllAbsentedOtherThisDay(id))
                .build();

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
