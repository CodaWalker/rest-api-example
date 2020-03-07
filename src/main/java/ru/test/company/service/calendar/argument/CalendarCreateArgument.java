package ru.test.company.service.calendar.argument;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CalendarCreateArgument {

    private final Event event;
    private final LocalDateTime startIntervalDate;
    private final LocalDateTime endIntervalDate;
    private final Employee employee;

    public CalendarCreateArgument(Event event, LocalDateTime startIntervalDate, LocalDateTime endIntervalDate, Employee employee) {

//       Validator.validateObjectParam(name, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
//       Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.event = event;
        this.startIntervalDate = startIntervalDate;
        this.endIntervalDate = endIntervalDate;
        this.employee = employee;
    }


}