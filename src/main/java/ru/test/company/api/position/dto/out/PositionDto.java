package ru.test.company.api.position.dto.out;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Полная ДТО для сущности "Должность"
 * */

@Getter
@Setter
@ApiModel("Должность")
public class PositionDto {

    @ApiModelProperty("Идентификатор должности")
    private UUID id;

    @ApiModelProperty("Название должности")
    private String name;

}
