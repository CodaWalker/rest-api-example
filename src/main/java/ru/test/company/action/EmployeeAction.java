package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    public  Employee execute(EmployeeCreateArgument employeeCreateArgument) throws ErrorCustom {
         return null;
    }

}
