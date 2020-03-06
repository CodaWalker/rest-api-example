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
import java.util.List;

@Component
public class GetCompanyActionImpl implements GetCompanyAction{

    private final EmployeeService employeeService;
    private final CalendarService calendarService;

    @Autowired
    public GetCompanyActionImpl(EmployeeService employeeService, CalendarService calendarService) {
        this.employeeService = employeeService;
        this.calendarService = calendarService;
    }


    @Override
    public Calendar createCalendar(CalendarCreateArgument calendarCreateArgument, Event event) throws ErrorCustom {
        Employee employee = employeeService.getExisting(calendarCreateArgument.getEmployeeId());

        if(employee.getLastWorkingDate() != null){
            throw new ErrorCustom(1,"Этот сотрудник уволен ранее");
        }
        LocalDateTime startIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(calendarCreateArgument.getStartIntervalDate());
        LocalDateTime finishIntervalDate = SimpleData.convertSimpleDataToLocalDateTime(calendarCreateArgument.getEndIntervalDate());
        Calendar calendar = Calendar.builder()
                .employee(employee)
                .event(event)
                .startIntervalDate(startIntervalDate)
                .endIntervalDate(finishIntervalDate)
                .build();

        calendarService.createCalendar(calendar);

        return calendar;

    }

    @Override
    public Calendar setAbsentedHolidayEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return  createCalendar(calendarCreateArgument,Event.ABSENTED_HOLIDAY);
    }

    @Override
    public Calendar setPresenceAtWorkEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        employeeService.setPresenceAtWorkEmployee(calendarCreateArgument.getEmployeeId());
        return  createCalendar(calendarCreateArgument,Event.PRESENCE_AT_WORK);
    }

    @Override
    public Calendar setAbsentedMedicalEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return createCalendar(calendarCreateArgument,Event.ABSENTED_MEDICAL);
    }

    @Override
    public Calendar setAbsentedOtherEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return createCalendar(calendarCreateArgument,Event.ABSENTED_OTHER);
    }

    @Override
    public List<Calendar> getAll() {
        return calendarService.getAll();
    }

    @Override
    public <T extends BaseEntity> void execute() {

    }
}
