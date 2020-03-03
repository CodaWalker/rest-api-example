package ru.test.company.service.department.argument;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class DepartmentUpdateArgument {
    public DepartmentUpdateArgument(String name,LocalDateTime firstWorkingDate, LocalDateTime lastWorkingDate, Integer department_id) {

//        Validator.validateObjectParam(userId, TicketError.TICKET_USER_ID_IS_MANDATORY);
//        Validator.validateObjectParam(name, TicketError.TICKET_NAME_IS_MANDATORY);
//        Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.name = name;
        this.firstWorkingDate = firstWorkingDate;
        this.lastWorkingDate = lastWorkingDate;
        this.department_id = department_id;
    }
    private String name;
    private LocalDateTime lastWorkingDate;
    private LocalDateTime firstWorkingDate;
    private Integer department_id;
}
