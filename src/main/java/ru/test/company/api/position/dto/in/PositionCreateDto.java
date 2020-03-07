package ru.test.company.api.position.dto.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** ДТО создания должности */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PositionCreateDto {
    @ApiModelProperty("Название должности")
    private String name;
}
