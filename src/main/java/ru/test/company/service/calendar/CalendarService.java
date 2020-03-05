package ru.test.company.service.calendar;

import ru.test.company.model.calendar.Calendar;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import java.util.List;
import java.util.UUID;

public interface CalendarService {
    Calendar createCalendar(CalendarCreateArgument departmentCreateArgument);
    Calendar updateCalendar(UUID departmentId, CalendarUpdateArgument calendarUpdateArgument);
    void removeCalendar(UUID calendarId);
    Calendar getExisting(UUID calendarId);
    List<Calendar> getAll();
    List<Calendar> getAllCalendarByEmployeeId(UUID calendarId);
}
