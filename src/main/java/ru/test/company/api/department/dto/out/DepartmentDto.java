package ru.test.company.api.department.dto.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Полная ДТО для сущности "Отдел"
 * */

@Getter
@Setter
@ApiModel("Отдел")
public class DepartmentDto {

    @ApiModelProperty("Идентификатор отдела")
    private UUID id;

    @ApiModelProperty("Название отдела")
    private String name;

    @ApiModelProperty("Дата первого рабочего дня отдела")
    private LocalDateTime firstWorkingDate;

    @ApiModelProperty("Дата последнего рабочего дня отдела")
    private LocalDateTime lastWorkingDate;
}
