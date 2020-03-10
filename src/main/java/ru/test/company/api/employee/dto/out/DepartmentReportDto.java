package ru.test.company.api.employee.dto.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Полная ДТО для сущности "Отчет посещаемости отдела"
 * */

@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel("Общий отчет посещаемости сотрудников отдела")
public class DepartmentReportDto {

    @ApiModelProperty("Количество сотрудников в отделе")
    private Long countAllEmployees;

    @ApiModelProperty("Количество сотрудников прогулявших без причины")
    private Long countAllWorkingEmployees;

    @ApiModelProperty("Количество сотрудников прогулявших без причины")
    private Long countOtherAbsentedEmployees;

    @ApiModelProperty("Количество сотрудников в отпуске")
    private Long countHolidayAbsentedEmployees;

    @ApiModelProperty("Количество сотрудников на больничном")
    private Long countMedicalAbsentedEmployees;

}
