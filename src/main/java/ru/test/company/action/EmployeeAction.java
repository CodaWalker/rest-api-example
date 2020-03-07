package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.time.LocalDateTime;

@Component
public class EmployeeAction {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeAction(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public Employee execute(EmployeeCreateDto dto) throws ErrorCustom {
        Department department = getDepartment(dto.getDepartment_name());
        if(department == null){
            department = departmentService.getByName("noDepartment");
        }
        EmployeeCreateArgument argument = getEmployeeCreateArgument(dto, department);
        return employeeService.createEmployee(argument);
    }

    private EmployeeCreateArgument getEmployeeCreateArgument(EmployeeCreateDto dto, Department department) {
        return EmployeeCreateArgument.builder().firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .firstWorkingDate(LocalDateTime.now())
                    .department(department).build();
    }

    public Employee execute(EmployeeUpdateDto dto) throws ErrorCustom {
        Department department = getDepartment(dto.getDepartment_name());
        if(department == null){
            department = departmentService.getByName("noDepartment");
        }
        EmployeeUpdateArgument argument = getEmployeeUpdateArgument(dto, department);
        return employeeService.updateEmployee(department.getId(),argument);
    }

    private EmployeeUpdateArgument getEmployeeUpdateArgument(EmployeeUpdateDto dto, Department department) {
        return EmployeeUpdateArgument.builder().firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .firstWorkingDate(LocalDateTime.now())
                .department(department).build();
    }

    private Department getDepartment(String name) {
        return departmentService.getByName(name);
    }

}
