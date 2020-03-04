package ru.test.company.service.employee;

import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.model.employee.Employee;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee createEmployee(EmployeeCreateArgument employeeCreateArgument);
    Employee getExisting(UUID employeeId);
    Employee updateEmployee(EmployeeUpdateArgument employeeUpdateArgument);
    void removeEmployee(UUID employeeId);

    List<Employee> getAll();

    Employee dismissEmployee(UUID id);
}
