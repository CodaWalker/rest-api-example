package ru.test.company.service.employee.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.error.EmployeeError;
import ru.test.company.model.department.Department;
import ru.test.company.util.validator.Validator;

import java.time.LocalDateTime;

/** Аргумент создания сотрудника */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeCreateArgument {

    private final String firstName;
    private final String lastName;
    private final LocalDateTime firstWorkingDate;
    private final Department department;
    private final Boolean presenceAtWork;

    public EmployeeCreateArgument(String firstName, Boolean presenceAtWork, String lastName, LocalDateTime firstWorkingDate, Department department) {

        Validator.validateObjectParam(firstName, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
        Validator.validateObjectParam(lastName, EmployeeError.EMPLOYEE_LAST_NAME_IS_MANDATORY);
        Validator.validateObjectParam(department, EmployeeError.EMPLOYEE_DEPARTMENT_ID_IS_MANDATORY);

        this.firstName = firstName;
        this.lastName = lastName;
        this.firstWorkingDate = firstWorkingDate;
        this.department = department;
        this.presenceAtWork = presenceAtWork;
    }


}