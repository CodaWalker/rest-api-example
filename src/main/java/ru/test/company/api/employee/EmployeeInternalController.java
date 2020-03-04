package ru.test.company.api.employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.api.employee.mapper.EmployeeMapper;
import ru.test.company.error.api.ApiExceptionHandler;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/employee")
@Api("Внутренний контроллер сотрудников")

public class EmployeeInternalController{

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeInternalController(EmployeeService employeeService, DepartmentService departmentService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.employeeMapper = employeeMapper;
    }

    @ApiOperation("Получить список сотрудников")
    @GetMapping(value = "/all")
    public List<EmployeeDto> getAll() {
        return employeeMapper.toDtoListFromDB(employeeService.getAll());
    }

    @ApiOperation("Создать сотрудника")
    @PostMapping(value = "/create")
    @ResponseStatus(CREATED)
    public EmployeeDto create(@RequestBody EmployeeCreateDto dto) {
            Department department = departmentService.getByName(dto.getDepartment_name());
            if(department == null){
                department = departmentService.getByName("NoDepartment");
            }
            return employeeMapper.toDto(employeeService.createEmployee(
                    EmployeeCreateArgument.builder().firstName(dto.getFirstName())
                            .lastName(dto.getLastName())
                            .firstWorkingDate(LocalDateTime.now())
                            .department_id(department.getId()).build()));
    }

    @ApiOperation("Уволить сотрудника")
    @PutMapping("/dismiss/{id}")
    public EmployeeDto dismiss(@PathVariable UUID id) {
        return employeeMapper.toDto(employeeService.dismissEmployee(id));
    }

    @ApiOperation("Удалить сотрудника")
    @PutMapping("/delete/{id}")
    public EmployeeDto delete(@PathVariable UUID id) {
        return employeeMapper.toDto(employeeService.deleteEmployee(id));
    }

    @ApiOperation("Получить сотрудника по идентификатору")
    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable UUID id) {
        return employeeMapper.toDto(employeeService.getExisting(id));
    }

    @ApiOperation("Обновить сотрудника")
    @PutMapping("/update/{id}")
    public EmployeeDto update(@PathVariable UUID id,
                            @RequestBody EmployeeUpdateDto updateDto) {
         return employeeMapper.toDto(employeeService.updateEmployee(id, EmployeeUpdateArgument.builder()
                                        .firstName(updateDto.getFirstName())
                                        .lastName(updateDto.getLastName())
                                        .department_id(UUID.fromString(updateDto.getDepartment_name()))
                                        .event(updateDto.getEvent())
                                        .build()));
    }
}
