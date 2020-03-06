package ru.test.company.action;

import ru.test.company.error.ErrorCustom;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;

import java.util.List;

public interface GetCompanyAction  {
     Calendar createCalendar(CalendarCreateArgument calendarCreateArgument, Event event) throws ErrorCustom;

    List<Calendar> getAll();

    <T extends BaseEntity> void execute();
}