package ru.test.company.api.calendar.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Полная ДТО для сущности "Отдел"
 * */

@Getter
@Setter
@ApiModel("Отдел")
public class CalendarDto {

    @ApiModelProperty("Идентификатор отдела")
    private UUID id;

    @ApiModelProperty("Дата начала события")
    private LocalDateTime startIntervalDate;

    @ApiModelProperty("Дата окончания события")
    private LocalDateTime endIntervalDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("ИД сотрудника")
    private UUID employee_id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Имя сотрудника")
    private String employee_firstName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Фамилия сотрудника")
    private String employee_lastName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("ИД департамента сотрудника")
    private UUID employee_departmentId;
}
