package ru.test.company.api.employee.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/** ДТО обновления сотрудника */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeUpdateDto {

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

    @ApiModelProperty("Событие связанное с пребыванием или отстутсвием сотрудника")
    private Event event;
}
