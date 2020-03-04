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
//
//    @ApiOperation("Получить заявку по идентификатору")
//    @GetMapping("/{id}")
//    public TicketDto get(@PathVariable UUID id) {
//        return ticketMapper.toDto(ticketService.getExisting(id));
//    }
//
//    @ApiOperation("Удалить заявку по идентификатору")
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable UUID id) {
//         ticketService.deleteTicket(id); //Add DTO
//    }
//
//    @ApiOperation("Подтвердить заявку")
//    @PutMapping("/{id}/accepted")
//    public TicketDto accepted(@PathVariable UUID id) {
//        return ticketMapper.toDto(ticketService.accepted(id)); //DTO ???
//    }
//
//    @ApiOperation("Обновить заявку")
//    @PutMapping("/{id}/update")
//    public TicketDto update(@PathVariable UUID id,
//                            @RequestBody TicketUpdateDto updateDto) {
//        return ticketMapper.toDto(ticketService.update(id, TicketUpdateArgument.builder()
//                                                                             .userId(updateDto.getUserId())
//                                                                              .comment(updateDto.getComment())
//                                                                                .sender_id(updateDto.getSender_id())
//                                                                                .recipient_id(updateDto.getRecipient_id())
//                                                                                .manager_id(updateDto.getManager_id())
//                                                                                .operator_id(updateDto.getOperator_id())
//                                                                                .accepted(updateDto.getAccepted())
//                                                                              .fromAds(updateDto.getFromAds())
//                                                                              .description(updateDto.getDescription())
//                                                                              .title(updateDto.getTitle())
//                                                                              .status(updateDto.getStatus())
//                                                                              .build()));
//    }
//

}
