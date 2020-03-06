package ru.test.company.service.employee.argument;

import lombok.*;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
/** Аргумент обновления сотрудника */
public class EmployeeUpdateArgument {
    public EmployeeUpdateArgument(String firstName, Boolean presenceAtWork, String lastName, LocalDateTime firstWorkingDate, LocalDateTime lastWorkingDate, UUID department_id, Event event) {

//        Validator.validateObjectParam(userId, TicketError.TICKET_USER_ID_IS_MANDATORY);
//        Validator.validateObjectParam(name, TicketError.TICKET_NAME_IS_MANDATORY);
//        Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.firstName = firstName;
        this.lastName = lastName;
        this.firstWorkingDate = firstWorkingDate;
        this.lastWorkingDate = lastWorkingDate;
        this.department_id = department_id;
        this.event = event;
        this.presenceAtWork = presenceAtWork;


    }
    private String firstName;
    private String lastName;
    private LocalDateTime lastWorkingDate;
    private LocalDateTime firstWorkingDate;
    private UUID department_id;
    private Event event;
    private Boolean presenceAtWork;

}
