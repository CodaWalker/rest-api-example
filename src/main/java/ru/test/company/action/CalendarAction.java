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
        LocalDateTime finishIntervalDate;
        try {
           startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getStartIntervalDate());
           finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getEndIntervalDate());

        }catch (ErrorCustom e){
            return getCalendar(dto, employee, startIntervalDate, startIntervalDate);
        }

        return getCalendar(dto, employee, startIntervalDate, finishIntervalDate);
    }

    private Calendar getCalendar(CalendarCreateDto dto, Employee employee, LocalDateTime startIntervalDate, LocalDateTime finishIntervalDate) throws ErrorCustom {
        if(startIntervalDate.getDayOfMonth() == finishIntervalDate.getDayOfMonth() &&
                startIntervalDate.getDayOfYear() == finishIntervalDate.getDayOfYear() &&
                startIntervalDate.getDayOfYear() == LocalDateTime.now().getDayOfYear() &&
                startIntervalDate.getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
        ){
            if(dto.getEvent() != Event.PRESENCE_AT_WORK) {
                employeeService.setAbsentedAtWorkEmployee(employee.getId());
            }else if(dto.getEvent() == Event.PRESENCE_AT_WORK){
                employeeService.setPresenceAtWorkEmployee(employee.getId());
            }
        }

//        Calendar calendar = calendarService.getAllByEmployeeIdOrderByFinishIntervalDateAndEvent(employee.getId(), startIntervalDate.minusDays(1), dto.getEvent());
//          if(calendar != null){
//              System.out.println("calendar: ");
//              System.out.println(calendar.getId());
//              System.out.println(calendar.getEmployee());
//              System.out.println(calendar.getStartIntervalDate());
//              System.out.println(calendar.getEndIntervalDate());
//              System.out.println(calendar.getEvent());
//          }else{
//              System.out.println("null calendar");
//          }

            // Обновляем вчерашнюю запись если находим крайний интервал равным вчера

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
