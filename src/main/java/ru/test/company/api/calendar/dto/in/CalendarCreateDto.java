package ru.test.company.api.calendar.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

/** ДТО создания календаря */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CalendarCreateDto {

    @ApiModelProperty("Дата первого рабочего дня отдела")
    private SimpleData startIntervalDate;

    @ApiModelProperty("Дата последнего рабочего дня отдела")
    private SimpleData endIntervalDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("ИД сотрудника")
    private UUID employeeId;

    @ApiModelProperty("Событие связанное с пребыванием или отстутсвием сотрудника")
    private Event event;
}
