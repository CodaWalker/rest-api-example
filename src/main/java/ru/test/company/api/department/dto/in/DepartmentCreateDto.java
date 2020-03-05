package ru.test.company.api.department.dto.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** ДТО создания отдела */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class DepartmentCreateDto {
    @ApiModelProperty("Название отдела")
    private String name;
}
