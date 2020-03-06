package ru.test.company.service.employee.argument;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.error.EmployeeError;
import ru.test.company.util.validator.Validator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/** Аргумент создания сотрудника */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeCreateArgument {
    public EmployeeCreateArgument(String firstName, Boolean presenceAtWork, String lastName, LocalDateTime firstWorkingDate, UUID department_id) {

        Validator.validateObjectParam(firstName, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
        Validator.validateObjectParam(lastName, EmployeeError.EMPLOYEE_LAST_NAME_IS_MANDATORY);
        Validator.validateObjectParam(department_id, EmployeeError.EMPLOYEE_DEPARTMENT_ID_IS_MANDATORY);

        this.firstName = firstName;
        this.lastName = lastName;
        this.firstWorkingDate = firstWorkingDate;
        this.department_id = department_id;
        this.presenceAtWork = presenceAtWork;
    }

    private String firstName;
    private String lastName;
    private LocalDateTime firstWorkingDate;
    private UUID department_id;
    private Boolean presenceAtWork;
}