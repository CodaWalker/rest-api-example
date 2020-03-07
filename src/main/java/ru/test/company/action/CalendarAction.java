package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.employee.EmployeeService;

import java.time.LocalDateTime;

@Component
public class CalendarAction {

    private final EmployeeService employeeService;
    private final CalendarService calendarService;

    @Autowired
    public CalendarAction(EmployeeService employeeService, CalendarService calendarService) {
        this.employeeService = employeeService;
        this.calendarService = calendarService;
    }

    public  Calendar execute(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        Employee employee = getEmployee(calendarCreateArgument);

        if(employee.getLastWorkingDate() != null){
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        }
        LocalDateTime startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(calendarCreateArgument.getStartIntervalDate());
        LocalDateTime finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(calendarCreateArgument.getEndIntervalDate());
        Calendar calendar = Calendar.builder()
                .employee(employee)
                .event(calendarCreateArgument.getEvent())
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(finishIntervalDate)
                .build();
         return calendarService.createCalendar(calendar);
    }

    private Employee getEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        if(!calendarCreateArgument.getEvent().equals(Event.PRESENCE_AT_WORK)) {
            employeeService.setPresenceAtWorkEmployee(calendarCreateArgument.getEmployeeId());
        }else {
            employeeService.setAbsentedAtWorkEmployee(calendarCreateArgument.getEmployeeId());
        }
        return employeeService.getExisting(calendarCreateArgument.getEmployeeId());
    }

}
