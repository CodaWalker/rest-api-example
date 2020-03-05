package ru.test.company.service.employee;

import ru.test.company.model.employee.Employee;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee createEmployee(EmployeeCreateArgument employeeCreateArgument);
    Employee getExisting(UUID employeeId);
    Employee updateEmployee(UUID uuid, EmployeeUpdateArgument employeeUpdateArgument);
    void removeEmployee(UUID employeeId);
    List<Employee> getAll();
    Employee dismissEmployee(UUID uuid);
    Employee deleteEmployee(UUID uuid);
    Long getCountDaysInCompany(UUID uuid);

    Long getCountWorkDaysInCompany(UUID uuid);

    Employee setAbsentedHolidayEmployee(UUID uuid);

    Employee setAbsentedMedicalEmployee(UUID uuid);

    Employee setAbsentedOtherEmployee(UUID uuid);
}
