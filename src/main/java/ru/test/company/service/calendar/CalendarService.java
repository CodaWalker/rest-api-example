package ru.test.company.service.calendar;

import ru.test.company.error.ErrorCustom;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CalendarService {
    Calendar createCalendar(CalendarCreateArgument argument) throws ErrorCustom;
    Calendar updateCalendar(UUID calendarId, CalendarUpdateArgument calendarUpdateArgument);
    void removeCalendar(UUID calendarId);
    Calendar getExisting(UUID calendarId);
    List<Calendar> getAll();

    LocalDate getLatestHolidayInCompany(UUID id) ;

    Calendar getAllByEmployeeIdOrderByFinishIntervalDateAndEvent(UUID id,LocalDate end,Event event);
}
