package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.calendar.dto.in.CalendarCreateDto;
import ru.test.company.api.calendar.dto.in.CalendarSearchDto;
import ru.test.company.api.calendar.dto.in.CalendarUpdateDto;
import ru.test.company.api.employee.dto.in.EmployeeUpdateDto;
import ru.test.company.error.EmployeeError;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.model.position.Position;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;
import ru.test.company.service.employee.EmployeeService;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;
import ru.test.company.util.validator.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
        Validator.validateObjectParam(employee, EmployeeError.EMPLOYEE_FIRED);
        Validator.validateObjectParam(employee.getLastWorkingDate(), EmployeeError.EMPLOYEE_FIRED,false);

        if(employee.getLastWorkingDate() != null){
//            Validator.validateObjectParam(employee.getLastWorkingDate(),Error);
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        }
        if(employee.getDepartment().getName().equals("noDepartment")) {
            throw new ErrorCustom(5,"У сотрудника не установлен отдел");
        }
        if(employee.getPosition() != null && employee.getDepartment() != null && employeeService.getByEmployeesByEmployeeIdAndPositionId(employee.getPosition().getId(),employee.getDepartment().getId()) < 2) {
            throw new ErrorCustom(6,"В данном отделе нет больше сотрудников одной должности");
        }
        LocalDate startIntervalDate = LocalDate.now();
        LocalDate finishIntervalDate;
        try {
           startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getStartIntervalDate());
           finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(dto.getEndIntervalDate());
        }catch (ErrorCustom e){
            return getCalendar(dto, employee, startIntervalDate, startIntervalDate);
        }
        return getCalendar(dto, employee, startIntervalDate, finishIntervalDate);
    }


    public Calendar execute(UUID id, CalendarUpdateDto dto) throws ErrorCustom {
        UUID employeeId = dto.getEmployeeId();
        if(dto.getEvent().equals(Event.PRESENCE_AT_WORK)) {
            employeeService.setPresenceAtWorkEmployee(employeeId);
        }else {
            employeeService.setAbsentedAtWorkEmployee(employeeId);
        }
        Employee employee = employeeService.getExisting(employeeId);
        return getCalendar(id, dto, employee);

    }
    private Calendar getCalendar(UUID id, CalendarUpdateDto dto, Employee employee) throws ErrorCustom {
        final LocalDate startIntervalDate = dto.getStartIntervalDate();
        final LocalDate endIntervalDate = dto.getEndIntervalDate();
        atWorkEmployee(dto.getEvent(), employee, startIntervalDate, endIntervalDate);


        CalendarUpdateArgument argument = CalendarUpdateArgument.builder()
                .event(dto.getEvent())
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(endIntervalDate)
                .employee(employee)
                .build();


        return calendarService.updateCalendar(id, argument);
    }

    private Calendar getCalendar(CalendarCreateDto dto, Employee employee, LocalDate startIntervalDate, LocalDate finishIntervalDate) throws ErrorCustom {
        atWorkEmployee(dto.getEvent(), employee, startIntervalDate, finishIntervalDate);

        //        Calendar calendar = calendarService.getAllByEmployeeIdOrderByFinishIntervalDateAndEvent(employee.getId(), startIntervalDate, dto.getEvent());
        // Обновляем вчерашнюю запись если находим крайний интервал равным вчера

        CalendarCreateArgument argument = CalendarCreateArgument.builder()
                .event(dto.getEvent())
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(finishIntervalDate)
                .employee(employee)
                .build();
        return calendarService.createCalendar(argument);
    }

    private void atWorkEmployee(Event event, Employee employee, LocalDate startIntervalDate, LocalDate finishIntervalDate) throws ErrorCustom {
        if(startIntervalDate.getDayOfMonth() == finishIntervalDate.getDayOfMonth() &&
                startIntervalDate.getDayOfYear() == finishIntervalDate.getDayOfYear() &&
                startIntervalDate.getDayOfYear() == LocalDateTime.now().getDayOfYear() &&
                startIntervalDate.getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
        ){
            if(event != Event.PRESENCE_AT_WORK) {
                employeeService.setAbsentedAtWorkEmployee(employee.getId());
            }else {
                employeeService.setPresenceAtWorkEmployee(employee.getId());
            }
        }
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
