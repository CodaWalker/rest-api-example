package ru.test.company.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.repository.calendar.CalendarRepository;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@Service
public class CalendarServiceImpl implements CalendarService{
    private final CalendarRepository calendarRepository;


    @Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public Calendar createCalendar(CalendarCreateArgument argument) throws ErrorCustom {
        Calendar calendar = Calendar.builder()
                .event(argument.getEvent())
                .startIntervalDate(argument.getStartIntervalDate())
                .endIntervalDate(argument.getEndIntervalDate())
                .build();
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




}
