package ru.test.company.service.calendar.argument;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CalendarCreateArgument {
    public CalendarCreateArgument(Event event, LocalDateTime startIntervalDate, LocalDateTime endIntervalDate) {

//       Validator.validateObjectParam(name, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
//       Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.startIntervalDate = startIntervalDate;
        this.endIntervalDate = endIntervalDate;
        this.event = event;
    }

    private LocalDateTime startIntervalDate;
    private LocalDateTime endIntervalDate;
    private Event event;
}