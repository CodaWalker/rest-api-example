package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.position.Position;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;
import ru.test.company.service.position.PositionService;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class EmployeeAction {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final CalendarService calendarService;

    @Autowired
    public EmployeeAction(EmployeeService employeeService, DepartmentService departmentService, PositionService positionService, CalendarService calendarService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.calendarService = calendarService;
    }

    public Employee execute(EmployeeCreateDto dto) {
        Position position = getPosition(dto.getPosition_name());

        Department department = getDepartment(dto.getDepartment_name());
        EmployeeCreateArgument argument = getEmployeeCreateArgument(dto, department, position);
        return employeeService.createEmployee(argument);
    }

    private Position getPosition(String name) {
        return positionService.getByName(name);
    }

    private EmployeeCreateArgument getEmployeeCreateArgument(EmployeeCreateDto dto, Department department, Position position) {
        return EmployeeCreateArgument.builder().firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .firstWorkingDate(LocalDateTime.now())
                    .department(department)
                    .position(position)
                    .build();
    }

    public Employee execute(UUID id, EmployeeUpdateDto dto) {
        Position position = getPosition(dto.getPosition_name());
        Department department = getDepartment(dto.getDepartment_name());
        EmployeeUpdateArgument argument = getEmployeeUpdateArgument(dto, department, position);
        return employeeService.updateEmployee(id,argument);
    }

    private EmployeeUpdateArgument getEmployeeUpdateArgument(EmployeeUpdateDto dto, Department department, Position position) {
        return EmployeeUpdateArgument.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .firstWorkingDate(LocalDateTime.now())
                .department(department)
                .position(position)
                .build();
    }

    private Department getDepartment(String name) {
        return departmentService.getByName(name);
    }

    public Employee deleteEmployee(UUID id) throws ErrorCustom {
        Employee employeeFromDB = employeeService.getExisting(id);
        if (employeeFromDB == null) {
            throw new ErrorCustom(3, "Не найден сотрудник");
        }
        if (employeeFromDB.getLastWorkingDate() == null) {
            throw new ErrorCustom(2, "Этот сотрудник не уволен, необходимо предварительно его уволить");
        } else {
            calendarService.removeCalendarsByEmployeeId(id);
            employeeService.deleteEmployee(employeeFromDB);
        }
        return employeeFromDB;
    }
}
