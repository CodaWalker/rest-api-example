package ru.test.company.api.employee.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** ДТО создания сотрудника */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeCreateDto {

    @ApiModelProperty("Имя сотрудника")
    private String firstName;

    @ApiModelProperty("Фамилия сотрудника")
    private String lastName;

    //В тестовых целях указываю на вход имя отдела
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Название отдела")
    private String department_name;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ApiModelProperty("ИД отдела")
//    private UUID department_id;

    //В тестовых целях указываю на вход название должности
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty("Название должности")
    private String position_name;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ApiModelProperty("ИД должности")
//    private UUID position_id;

}
