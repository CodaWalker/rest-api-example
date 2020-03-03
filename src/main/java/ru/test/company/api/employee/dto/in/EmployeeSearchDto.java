package ru.test.company.api.employee.dto.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/** ДТО поиска сотрудника */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeSearchDto {

    @ApiModelProperty("Имя сотрудника")
    private String firstName;

    @ApiModelProperty("Фамилия сотрудника")
    private String lastName;

    @ApiModelProperty("Дата первого рабочего дня")
    private LocalDateTime firstWorkingDate;

    @ApiModelProperty("Дата последнего рабочего дня")
    private LocalDateTime lastWorkingDate;

    @ApiModelProperty("Отдел сотрудника")
    private Integer department_id;

    @ApiModelProperty("Событие связанное с пребыванием или отстутсвием сотрудника")
    private Event event;
}
