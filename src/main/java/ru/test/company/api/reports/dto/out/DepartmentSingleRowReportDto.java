package ru.test.company.api.reports.dto.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Event;

import java.util.Map;

/**
 * Полная ДТО для сущности "Отчет посещаемости отдела по одному полю"
 * */

@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel("Отдельный очет по одному событию")
public class DepartmentSingleRowReportDto {

    @ApiModelProperty("Событие")
    private Event event;

    @ApiModelProperty("Количество сотрудников по событию")
    private Long countDaysEmployee;

}
