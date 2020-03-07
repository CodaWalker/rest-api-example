package ru.test.company.service.department.argument;

import lombok.Builder;
import lombok.Getter;
import ru.test.company.model.department.Department;

import java.time.LocalDateTime;

@Builder
@Getter
public class DepartmentUpdateArgument {

    private final String name;
    private final LocalDateTime lastWorkingDate;
    private final LocalDateTime firstWorkingDate;
    private final Department department;

    public DepartmentUpdateArgument(String name,LocalDateTime firstWorkingDate, LocalDateTime lastWorkingDate, Department department) {

//        Validator.validateObjectParam(userId, TicketError.TICKET_USER_ID_IS_MANDATORY);
//        Validator.validateObjectParam(name, TicketError.TICKET_NAME_IS_MANDATORY);
//        Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.name = name;
        this.firstWorkingDate = firstWorkingDate;
        this.lastWorkingDate = lastWorkingDate;
        this.department = department;
    }

}
