package ru.test.company.api.employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.action.EmployeeAction;
import ru.test.company.api.employee.dto.in.EmployeeCreateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.api.employee.dto.out.DepartmentReportDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.api.employee.mapper.EmployeeMapper;
import ru.test.company.error.ErrorCustom;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    @ApiOperation("Получить количество рабочих дней с начала трудоустройства")
    @GetMapping("/report/get-work-days/{id}")
    public Long getWorkDays(@PathVariable UUID id) {
        return employeeService.getCountWorkDaysInCompany(id);
    }


    @ApiOperation("Обновить сотрудника")
    @PutMapping("/update/{id}")
    public EmployeeDto update(@PathVariable UUID id,
                            @RequestBody EmployeeUpdateDto updateDto) throws ErrorCustom {
        return employeeMapper.toDto(employeeAction.execute(id,updateDto));

    }


//    @ApiOperation("Получить количество сотрудников, которые работают в отделе")
//    @GetMapping("/report/get-working-employees/{id}")
//    public Long getReportCountWorkingEmployees(@PathVariable UUID id) {
//        return employeeService.getReportAllWorkingThisDay(id);
//    }
//
//    @ApiOperation("Получить количество сотрудников, которые в отпуске сегодня")
//    @GetMapping("/report/get-holiday-employees/{id}")
//    public Long getReportCountHolidayEmployees(@PathVariable UUID id) {
//        return employeeService.getReportAllHolidayThisDay(id);
//    }
//
//    @ApiOperation("Получить количество сотрудников, которые на больничном сегодня")
//    @GetMapping("/report/get-medical-employees/{id}")
//    public Long getReportCountMedicalEmployees(@PathVariable UUID id) {
//        return employeeService.getReportAllMedicalThisDay(id);
//    }
//
//    @ApiOperation("Получить количество сотрудников, которые прогуливают сегодня")
//    @GetMapping("/report/get-absented-other-employees/{id}")
//    public Long getReportCountAbsentedOtherEmployees(@PathVariable UUID id) {
//        return employeeService.getReportAllAbsentedOtherThisDay(id);
//    }

    @ApiOperation("Получить общий отчет по пристутствию сотрудников в отделе на сегодня. Не забывайте отмечать сотрудников в Календаре!")
    @GetMapping("/report/get-count-all-employees/{id}")
    public DepartmentReportDto getCountAllEmployees(@PathVariable UUID id) {
       return employeeService.getReportAll(id);
    }
}


