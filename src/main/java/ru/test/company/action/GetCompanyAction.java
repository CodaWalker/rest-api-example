package ru.test.company.action;

import ru.test.company.error.ErrorCustom;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;

import java.util.List;

public interface GetCompanyAction  {
     Calendar createCalendar(CalendarCreateArgument calendarCreateArgument, Event event) throws Exception, ErrorCustom;


     Calendar setAbsentedHolidayEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;


     Calendar setPresenceAtWorkEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;


     Calendar setAbsentedMedicalEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;


     Calendar setAbsentedOtherEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;

    List<Calendar> getAll();

    <T extends BaseEntity> void execute();
}