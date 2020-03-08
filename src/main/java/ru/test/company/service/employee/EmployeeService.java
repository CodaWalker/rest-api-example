package ru.test.company.service.employee;

import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee createEmployee(EmployeeCreateArgument argument);
    Employee getExisting(UUID employeeId);
    Employee updateEmployee(UUID uuid, EmployeeUpdateArgument employeeUpdateArgument);
    void removeEmployee(UUID employeeId);
    List<Employee> getAll();
    Employee dismissEmployee(UUID uuid) throws ErrorCustom;
    Employee deleteEmployee(UUID uuid) throws ErrorCustom;
    Long getCountDaysInCompany(UUID uuid);

    Long getCountWorkDaysInCompany(UUID uuid);
    Employee setPresenceAtWorkEmployee(UUID id) throws ErrorCustom;
    Employee setAbsentedAtWorkEmployee(UUID id) throws ErrorCustom;

    Integer getByEmployeesByEmployeeIdAndPositionId(UUID positionId, UUID departmentId);
}
