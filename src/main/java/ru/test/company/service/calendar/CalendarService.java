package ru.test.company.service.calendar;

import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import java.util.List;
import java.util.UUID;

public interface CalendarService {
    Calendar createCalendar(Calendar calendar);
    Calendar updateCalendar(UUID calendarId, CalendarUpdateArgument calendarUpdateArgument);
    void removeCalendar(UUID calendarId);
    Calendar getExisting(UUID calendarId);
    List<Calendar> getAll();
    List<Calendar> getAllCalendarByEmployeeId(UUID calendarId);

    Calendar setAbsentedHolidayEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;
    Calendar setAbsentedMedicalEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;
    Calendar setPresenceAtWorkEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;
    Calendar setAbsentedOtherEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom;
}
