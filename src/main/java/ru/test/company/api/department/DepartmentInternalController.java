package ru.test.company.api.department;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.test.company.api.department.dto.in.DepartmentCreateDto;
import ru.test.company.api.department.dto.out.DepartmentDto;
import ru.test.company.api.department.mapper.DepartmentMapper;
import ru.test.company.api.reports.dto.out.DepartmentSingleRowReportDto;
import ru.test.company.model.department.Department;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.department.argument.DepartmentCreateArgument;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/department")
@Api("Внутренний контроллер отдела")
public class DepartmentInternalController {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    public DepartmentInternalController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @ApiOperation("Получить список отделов")
    @GetMapping(value = "/all")
    public List<DepartmentDto> getAll() {
        return departmentMapper.toDtoListFromDB(departmentService.getAll());
    }

    @ApiOperation("Создать отдел")
    @PostMapping(value = "/create")
    @ResponseStatus(CREATED)
    public DepartmentDto create(@RequestBody DepartmentCreateDto dto) {
        Department department = departmentService.getByName(dto.getName());
        if(department == null){
            return departmentMapper.toDto(
                    departmentService.createDepartment(DepartmentCreateArgument.builder()
                            .name(dto.getName())
                            .build())
            );
        }
        else return departmentMapper.toDto(department);
    }

    @ApiOperation("Получить количество всех дней с началы открытия отдела")
    @GetMapping("/report/get-all-days/{id}")
    public DepartmentSingleRowReportDto getAllDays(@PathVariable UUID id) {

        return   DepartmentSingleRowReportDto.builder()
                .countDaysEmployee(departmentService.getCountDaysInCompany(id))
                .event(null)
                .build();
    }

}
