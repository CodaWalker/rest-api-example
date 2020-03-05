package ru.test.company.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.calendar.CalendarRepository;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;
import ru.test.company.service.employee.EmployeeService;

import java.util.List;
import java.util.UUID;

@Service
public class CalendarServiceImpl implements CalendarService{
    private final CalendarRepository calendarRepository;
    private final EmployeeService employeeService;

    @Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository, EmployeeService employeeService) {
        this.calendarRepository = calendarRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Calendar createCalendar(CalendarCreateArgument calendarCreateArgument) {
        return calendarRepository.save(Calendar.builder()
                .employee(employeeService.getExisting(calendarCreateArgument.getEmployeeId()))
                .event(calendarCreateArgument.getEvent())
                .startIntervalDate(calendarCreateArgument.getStartIntervalDate())
                .endIntervalDate(calendarCreateArgument.getEndIntervalDate())
                .build());

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
