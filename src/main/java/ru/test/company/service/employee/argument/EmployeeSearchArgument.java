package ru.test.company.service.employee.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
/** Аргумент поиска сотрудника **/
public class EmployeeSearchArgument {

    private String firstName;
    private String lastName;
    private LocalDateTime lastWorkingDate;
    private LocalDateTime firstWorkingDate;
    private Integer department_id;
    private Boolean presenceAtWork;


}
