package ru.test.company.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.employee.Event;
import ru.test.company.repository.calendar.CalendarRepository;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.calendar.argument.CalendarUpdateArgument;

import java.time.LocalDate;
import java.util.*;

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
    public Calendar getAllByEmployeeIdOrderByFinishIntervalDateAndEvent(UUID id, LocalDate end, Event event) {
        return calendarRepository.getCalendarByEmployeeIdAndEventAndEndIntervalDate(id, event, end);
    }

    @Override
    public LocalDate getCalendarByLastDateAndEvent(UUID id, Event event) throws ErrorCustom {
        Set<Calendar> calendars = calendarRepository.findCalendarByEmployeeIdAndEventOrderByEndIntervalDateDesc(id, event);
        Set<Calendar> sortedSet = new TreeSet<>(new Comparator<Calendar>() {
            public int compare(Calendar o1, Calendar o2) {
                return o2.getEndIntervalDate().compareTo(o1.getEndIntervalDate());
            }
        });
        sortedSet.addAll(calendars);

        if(calendars.size() != 0){
            return  sortedSet.iterator().next().getEndIntervalDate();
        }
            throw new ErrorCustom(6,"У сотрудника нет событий связанных с " + event.name());
    }

    @Override
    public List<Calendar> getAllByEmployeeId(UUID id) {
        return calendarRepository.getCalendarsByEmployee_Id(id);
    }

    @Override
    public Long countWorkDaysAllByEmployeeId(UUID id) {
        final List<Calendar> calendars = calendarRepository.getCalendarsByEmployee_IdAndEvent(id, Event.PRESENCE_AT_WORK);

        return 1L;
    }
}
