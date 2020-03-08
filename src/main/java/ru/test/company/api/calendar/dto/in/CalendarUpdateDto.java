package ru.test.company.api.calendar.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/** ДТО обновления календаря */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CalendarUpdateDto {

    @ApiModelProperty("Дата первого рабочего дня отдела")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startIntervalDate;

    @ApiModelProperty("Дата последнего рабочего дня отдела")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endIntervalDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("ИД сотрудника")
    private UUID employee_id;

    @ApiModelProperty("Событие связанное с пребыванием или отстутсвием сотрудника")
    private Event event;
}
