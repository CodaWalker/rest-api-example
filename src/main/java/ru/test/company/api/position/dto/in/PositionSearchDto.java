package ru.test.company.api.position.dto.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** ДТО поиска должности */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PositionSearchDto {
    @ApiModelProperty("Название должности")
    private String name;
}
