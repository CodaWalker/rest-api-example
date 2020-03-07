package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.calendar.dto.in.CalendarCreateDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.util.validator.Validator;

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

    public  Calendar execute(CalendarCreateDto dto) throws ErrorCustom {
        Employee employee = getEmployee(dto);

        if(employee.getLastWorkingDate() != null){
//            Validator.validateObjectParam(employee.getLastWorkingDate(),Error);
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        }
        LocalDateTime startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getStartIntervalDate());
        LocalDateTime finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getEndIntervalDate());
        CalendarCreateArgument argument = CalendarCreateArgument.builder()
                .event(dto.getEvent())
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(finishIntervalDate)
                .employee(employee)
                .build();
         return calendarService.createCalendar(argument);
    }

    private Employee getEmployee(CalendarCreateDto dto) throws ErrorCustom {
        if(dto.getEvent().equals(Event.PRESENCE_AT_WORK)) {
            employeeService.setPresenceAtWorkEmployee(dto.getEmployeeId());
        }else {
            employeeService.setAbsentedAbsentedAtWorkEmployee(dto.getEmployeeId());
        }
        return employeeService.getExisting(dto.getEmployeeId());
    }

}
