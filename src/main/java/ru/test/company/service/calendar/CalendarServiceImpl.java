package ru.test.company.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.calendar.CalendarRepository;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Transactional
    public Calendar createCalendar(CalendarCreateArgument argument) throws ErrorCustom {
        Calendar calendar = Calendar.builder()
                .event(argument.getEvent())
                .startIntervalDate(argument.getStartIntervalDate())
                .endIntervalDate(argument.getEndIntervalDate())
                .employee(argument.getEmployee())
                .build();
        return calendarRepository.save(calendar);
    }

    @Override
    @Transactional
    public Calendar updateCalendar(UUID id, CalendarUpdateArgument argument) {
        Calendar calendar = Calendar.builder()
                .event(argument.getEvent())
                .startIntervalDate(argument.getStartIntervalDate())
                .endIntervalDate(argument.getEndIntervalDate())
                .employee(argument.getEmployee())
                .build();
        calendar.setId(id);
        return calendarRepository.save(calendar);
    }

    @Override
    @Transactional
    public void removeCalendar(UUID calendarId) {
        calendarRepository.delete(getExisting(calendarId));
    }

    @Override
    @Transactional
    public Calendar getExisting(UUID calendarId) {
        return calendarRepository.getOne(calendarId);
    }

    @Override
    @Transactional
    public List<Calendar> getAll() {
        return calendarRepository.findAll();
    }


    @Override
    public List<Calendar> getAllByEmployeeIdOrderByFinishIntervalDateAndEvent(UUID id, LocalDate end, Event event) {
        return calendarRepository.getCalendarByEmployeeIdAndEventAndEndIntervalDate(id, Event.ABSENTED_HOLIDAY, end);
    }

    @Override
    public LocalDate getCalendarByLastDateAndEvent(UUID id, Event event) throws ErrorCustom {
        Calendar calendar = calendarRepository.findTopCalendarByEmployeeIdAndEventOrderByEndIntervalDateDesc(id, event);
        if(calendar != null){
            return calendar.getEndIntervalDate();
        }
            throw new ErrorCustom(6,"У сотрудника нет событий связанных с" + event.name());

    }


}
