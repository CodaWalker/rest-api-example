package ru.test.company.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.employee.EmployeeRepository;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

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
                .firstWorkingDate(employeeCreateArgument.getFirstWorkingDate())
                .department(departmentService.getExisting(employeeCreateArgument.getDepartment_id()))
                .event(Event.PRESENCE_AT_WORK)
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getExisting(UUID employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateArgument employeeUpdateArgument) {
        Employee employee = Employee.builder()
                .firstName(employeeUpdateArgument.getFirstName())
                .lastName(employeeUpdateArgument.getLastName())
                .firstWorkingDate(employeeUpdateArgument.getFirstWorkingDate())
                .lastWorkingDate(employeeUpdateArgument.getLastWorkingDate())
                .department(departmentService.getExisting(employeeUpdateArgument.getDepartment_id()))
                .event(employeeUpdateArgument.getEvent())
                .build();
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
}
