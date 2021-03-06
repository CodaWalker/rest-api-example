package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.calendar.dto.in.CalendarCreateDto;
import ru.test.company.api.calendar.dto.in.CalendarUpdateDto;
import ru.test.company.error.EmployeeError;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.util.validator.Validator;

import java.time.LocalDate;
import java.util.UUID;

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
        if(employee == null) {
            throw new ErrorCustom(3, "Не найден сотрудник");
        }
        final boolean isOtherEmpThisPosition = employee.getPosition() != null && employee.getDepartment() != null && employeeService
                .getByEmployeesByEmployeeIdAndPositionId(employee.getPosition().getId(), employee.getDepartment().getId()) < 2;
        if(employee.getLastWorkingDate() != null) {
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        }
        if(!dto.getEvent().equals(Event.PRESENCE_AT_WORK)) {
            if(employee.getDepartment() == null){
                throw new ErrorCustom(5,"У сотрудника не установлен отдел");
            }
            if(isOtherEmpThisPosition){
                throw new ErrorCustom(7,"В данном отделе нет больше сотрудников одной должности");
            }
        }
        LocalDate startIntervalDate = LocalDate.now();
        LocalDate finishIntervalDate;
        try {
            startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getStartIntervalDate());
            finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getEndIntervalDate());
        }catch (ErrorCustom e){
            return getCalendar(dto.getEvent(), employee, startIntervalDate, startIntervalDate);
        }
        return getCalendar(dto.getEvent(), employee, startIntervalDate, finishIntervalDate);
    }

    // метод который работает с интервалами. Необходим для проверки и слития записией в календаре
    private Calendar getCalendarByInterval(UUID employeeId, SimpleData end, Event event, int day) throws ErrorCustom {
        return calendarService
                .getAllByEmployeeIdOrderByFinishIntervalDateAndEvent(employeeId, SimpleData.convertSimpleDataToLocalDateTime(end).plusDays(day), event);
    }


    public Calendar execute(UUID id, CalendarUpdateDto dto) throws ErrorCustom {
        UUID employeeId = dto.getEmployeeId();
        Employee employee = employeeService.getExisting(employeeId);
        return getCalendar(id, dto.getEvent(), employee, dto.getStartIntervalDate(), dto.getEndIntervalDate());

    }
    private Calendar getCalendar(UUID id, Event event, Employee employee, LocalDate start, LocalDate end) throws ErrorCustom {
        CalendarUpdateArgument argument = CalendarUpdateArgument.builder()
                .event(event)
                .startIntervalDate(start)
                .endIntervalDate(end)
                .employee(employee)
                .build();
        return calendarService.updateCalendar(id, argument);
    }

    private Calendar getCalendar(Event event, Employee employee, LocalDate startIntervalDate, LocalDate finishIntervalDate) throws ErrorCustom {
        CalendarCreateArgument argument = CalendarCreateArgument.builder()
                .event(event)
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(finishIntervalDate)
                .employee(employee)
                .build();
        return calendarService.createCalendar(argument);
    }

    private Employee getEmployee(CalendarCreateDto dto) throws ErrorCustom {
        return employeeService.getExisting(dto.getEmployeeId());
    }
}
