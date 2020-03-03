package ru.test.company.service.department.argument;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DepartmentCreateArgument {
    public DepartmentCreateArgument(String name, LocalDateTime firstWorkingDate, LocalDateTime lastWorkingDate) {

//       Validator.validateObjectParam(name, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
//       Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.name = name;
        this.firstWorkingDate = firstWorkingDate;
        this.lastWorkingDate = lastWorkingDate;
    }
    private String name;
    private LocalDateTime lastWorkingDate;
    private LocalDateTime firstWorkingDate;
}

