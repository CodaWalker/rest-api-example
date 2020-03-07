package ru.test.company.service.calendar.argument;

import lombok.Builder;
import lombok.Getter;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CalendarUpdateArgument {

    private final Employee employee;
    private final Event event;
    private final SimpleData startIntervalDate;
    private final SimpleData endIntervalDate;

    public CalendarUpdateArgument(Employee employee, Event event, SimpleData startIntervalDate, SimpleData endIntervalDate) {

//       Validator.validateObjectParam(name, EmployeeError.EMPLOYEE_FIRST_NAME_IS_MANDATORY);
//       Validator.validateObjectParam(description, TicketError.TICKET_DESCRIPTION_IS_MANDATORY);

        this.employee = employee;
        this.event = event;
        this.startIntervalDate = startIntervalDate;
        this.endIntervalDate = endIntervalDate;
    }

}