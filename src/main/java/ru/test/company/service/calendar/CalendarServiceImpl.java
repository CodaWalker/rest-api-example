package ru.test.company.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.company.action.GetCompanyAction;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.calendar.CalendarRepository;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import java.util.List;
import java.util.UUID;

@Service
public class CalendarServiceImpl implements CalendarService{
    private final CalendarRepository calendarRepository;
    private final GetCompanyAction getCompanyAction;


    @Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository, GetCompanyAction getCompanyAction) {
        this.calendarRepository = calendarRepository;
        this.getCompanyAction = getCompanyAction;
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @Override
    public Calendar updateCalendar(UUID departmentId, CalendarUpdateArgument calendarUpdateArgument) {
        return null;
    }

    @Override
    public void removeCalendar(UUID calendarId) {
        calendarRepository.delete(getExisting(calendarId));
    }

    @Override
    public Calendar getExisting(UUID calendarId) {
        return calendarRepository.getOne(calendarId);
    }

    @Override
    public List<Calendar> getAll() {
        return calendarRepository.findAll();
    }

    @Override
    public List<Calendar> getAllCalendarByEmployeeId(UUID employeeId) {
        return calendarRepository.getCalendarsByEmployee_Id(employeeId);
    }
    @Override
    public Calendar setAbsentedHolidayEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return  getCompanyAction.createCalendar(calendarCreateArgument, Event.ABSENTED_HOLIDAY);
    }
    @Override
    public Calendar setPresenceAtWorkEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return  getCompanyAction.createCalendar(calendarCreateArgument,Event.PRESENCE_AT_WORK);
    }
    @Override
    public Calendar setAbsentedMedicalEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return getCompanyAction.createCalendar(calendarCreateArgument,Event.ABSENTED_MEDICAL);
    }
    @Override
    public Calendar setAbsentedOtherEmployee(CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return getCompanyAction.createCalendar(calendarCreateArgument,Event.ABSENTED_OTHER);
    }



}
