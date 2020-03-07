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

    private final String firstName;
    private final String lastName;
    private final LocalDateTime lastWorkingDate;
    private final LocalDateTime firstWorkingDate;
    private final Integer department_id;
    private final Boolean presenceAtWork;


}
