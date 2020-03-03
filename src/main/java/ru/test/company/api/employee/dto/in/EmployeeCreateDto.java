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

/** ДТО создания сотрудника */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeCreateDto {

    @ApiModelProperty("Имя сотрудника")
    private String firstName;

    @ApiModelProperty("Фамилия сотрудника")
    private String lastName;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Название отдела")
    private String department_name;

    @ApiModelProperty("Событие связанное с пребыванием или отстутсвием сотрудника")
    private Event event;
}
