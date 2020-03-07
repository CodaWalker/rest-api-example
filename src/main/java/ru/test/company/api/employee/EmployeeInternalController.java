package ru.test.company.api.employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.action.EmployeeAction;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.api.employee.mapper.EmployeeMapper;
import ru.test.company.error.ErrorCustom;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/employee")
@Api("Внутренний контроллер сотрудников")

public class EmployeeInternalController{

    private final EmployeeService employeeService;
    private final EmployeeAction employeeAction;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeInternalController(EmployeeService employeeService, DepartmentService departmentService, EmployeeAction employeeAction, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeAction = employeeAction;
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
    public EmployeeDto create(@RequestBody EmployeeCreateDto dto) throws ErrorCustom {
            return employeeMapper.toDto(employeeAction.execute(dto));
    }

    @ApiOperation("Уволить сотрудника")
    @PutMapping("/dismiss/{id}")
    public EmployeeDto dismiss(@PathVariable UUID id) throws ErrorCustom {
        return employeeMapper.toDto(employeeService.dismissEmployee(id));
    }

    @ApiOperation("Удалить сотрудника")
    @PutMapping("/delete/{id}")
    public EmployeeDto delete(@PathVariable UUID id) throws ErrorCustom {
        return employeeMapper.toDto(employeeService.deleteEmployee(id));
    }

    @ApiOperation("Получить сотрудника по идентификатору")
    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable UUID id) {
        return employeeMapper.toDto(employeeService.getExisting(id));
    }

    @ApiOperation("Получить количество всех дней")
    @GetMapping("/report/get-all-days/{id}")
    public Long getAllDays(@PathVariable UUID id) {
        return employeeService.getCountDaysInCompany(id);
    }

    @ApiOperation("Получить количество рабочих дней")
    @GetMapping("/report/get-work-days/{id}")
    public Long getWorkDays(@PathVariable UUID id) {
        return employeeService.getCountWorkDaysInCompany(id);
    }

    @ApiOperation("Обновить сотрудника")
    @PutMapping("/update/{id}")
    public EmployeeDto update(@PathVariable UUID id,
                            @RequestBody EmployeeUpdateDto updateDto) {
         return employeeMapper.toDto(employeeService.updateEmployee(id, EmployeeUpdateArgument.builder()
                                        .firstName(updateDto.getFirstName())
                                        .lastName(updateDto.getLastName())
                                        .presenceAtWork(updateDto.getPresenceAtWork())
//                                        .department(UUID.fromString(updateDto.getDepartment_name()))
                                        .event(updateDto.getEvent())
                                        .build()));
    }

}
