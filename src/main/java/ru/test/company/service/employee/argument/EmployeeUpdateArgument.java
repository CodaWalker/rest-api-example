package ru.test.company.service.employee.argument;

import lombok.*;
import ru.test.company.model.department.Department;
import ru.test.company.model.position.Position;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
/** Аргумент обновления сотрудника */
public class EmployeeUpdateArgument {

    private final UUID uuid;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime lastWorkingDate;
    private final LocalDateTime firstWorkingDate;
    private final Department department;
    private final Position position;
    private final Boolean presenceAtWork;

    public EmployeeUpdateArgument(UUID uuid, String firstName, Boolean presenceAtWork, String lastName, LocalDateTime firstWorkingDate, LocalDateTime lastWorkingDate, Department department, Position position) {


//        Validator.validateObjectParam(userId, TicketError.TICKET_USER_ID_IS_MANDATORY);
//        Validator.validateObjectParam(name, TicketError.TICKET_NAME_IS_MANDATORY);
//        Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstWorkingDate = firstWorkingDate;
        this.lastWorkingDate = lastWorkingDate;
        this.department = department;
        this.presenceAtWork = presenceAtWork;
        this.position = position;
    }


}
