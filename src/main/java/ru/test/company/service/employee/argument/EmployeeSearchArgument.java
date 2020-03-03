package ru.test.company.service.employee.argument;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
/** Аргумент поиска сотрудника **/
public class EmployeeSearchArgument {

    private String firstName;
    private String lastName;
    private LocalDateTime lastWorkingDate;
    private LocalDateTime firstWorkingDate;
    private Integer department_id;
}
