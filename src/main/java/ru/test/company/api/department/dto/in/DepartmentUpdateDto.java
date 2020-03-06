package ru.test.company.api.department.dto.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;

/** ДТО отдела */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class DepartmentUpdateDto {

    @ApiModelProperty("Название отдела")
    private String name;

    @ApiModelProperty("Дата первого рабочего дня отдела")
    private LocalDateTime firstWorkingDate;

    @ApiModelProperty("Дата последнего рабочего дня отдела")
    private LocalDateTime lastWorkingDate;
}
