package ru.test.company.service.calendar.argument;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.calendar.SimpleData;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CalendarCreateArgument {
    public CalendarCreateArgument(UUID employeeId, SimpleData startIntervalDate, SimpleData endIntervalDate) {

//       Validator.validateObjectParam(name, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
//       Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.employeeId = employeeId;
        this.startIntervalDate = startIntervalDate;
        this.endIntervalDate = endIntervalDate;
    }

    private UUID employeeId;
    private SimpleData startIntervalDate;
    private SimpleData endIntervalDate;

}