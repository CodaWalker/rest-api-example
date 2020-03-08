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
        if(employee.getDepartment().getName().equals("noDepartment")) {
            throw new ErrorCustom(5,"У сотрудника не установлен отдел");
        }
        if(employeeService.getByEmployeesByEmployeeIdAndPositionId(employee.getPosition().getId(),employee.getDepartment().getId()) < 2) {
            throw new ErrorCustom(6,"В данном отделе нет больше сотрудников одной должности");
        }

        LocalDateTime startIntervalDate = LocalDateTime.now();
        LocalDateTime finishIntervalDate = LocalDateTime.now();
        try {
           startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getStartIntervalDate());
           finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getEndIntervalDate());
        }catch (ErrorCustom e){
            CalendarCreateArgument argument = getCalendarCreateArgument(dto, employee, startIntervalDate, finishIntervalDate);
            return calendarService.createCalendar(argument);
        }
        CalendarCreateArgument argument = getCalendarCreateArgument(dto, employee, startIntervalDate, finishIntervalDate);
        return calendarService.createCalendar(argument);
    }

    private CalendarCreateArgument getCalendarCreateArgument(CalendarCreateDto dto, Employee employee, LocalDateTime startIntervalDate, LocalDateTime finishIntervalDate) {
        return CalendarCreateArgument.builder()
                .event(dto.getEvent())
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(finishIntervalDate)
                .employee(employee)
                .build();
    }

    private Employee getEmployee(CalendarCreateDto dto) throws ErrorCustom {
        if(dto.getEvent().equals(Event.PRESENCE_AT_WORK)) {
            employeeService.setPresenceAtWorkEmployee(dto.getEmployeeId());
        }else {
            employeeService.setAbsentedAtWorkEmployee(dto.getEmployeeId());
        }
        return employeeService.getExisting(dto.getEmployeeId());
    }
}
