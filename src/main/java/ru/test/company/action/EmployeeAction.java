package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.position.Position;
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

    @Autowired
    public EmployeeAction(EmployeeService employeeService, DepartmentService departmentService, PositionService positionService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.positionService = positionService;
    }

    public Employee execute(EmployeeCreateDto dto) throws ErrorCustom {
        Position position = getPosition(dto.getPosition_name());

        Department department = getDepartment(dto.getDepartment_name());
//        if(department == null){
//            department = departmentService.getByName("noDepartment");
//        }
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

    public Employee execute(UUID id, EmployeeUpdateDto dto) throws ErrorCustom {
        Position position = getPosition(dto.getPosition_name());
        Department department = getDepartment(dto.getDepartment_name());
//        if(department == null){
//            department = departmentService.getByName("noDepartment");
//        }
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

}
