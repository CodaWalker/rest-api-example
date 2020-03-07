package ru.test.company.api.employee.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Полная ДТО для сущности "Сотрудник"
 * */
@Getter
@Setter
@ApiModel("Сотрудник")
public class EmployeeDto {

    @ApiModelProperty("Идентификатор сотрудника")
    private UUID id;

    @ApiModelProperty("Имя сотрудника")
    private String firstName;

    @ApiModelProperty("Фамилия сотрудника")
    private String lastName;

    @ApiModelProperty("Дата первого рабочего дня")
    private LocalDateTime firstWorkingDate;

    @ApiModelProperty("Дата последнего рабочего дня")
    private LocalDateTime lastWorkingDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Название отдела")
    private String department_name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("ИД отдела сотрудника")
    private UUID department_id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Название должности отдела")
    private String position_name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("ИД должности сотрудника")
    private UUID position_id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Присутствие сотрудника")
    private Boolean presenceAtWork;
}
