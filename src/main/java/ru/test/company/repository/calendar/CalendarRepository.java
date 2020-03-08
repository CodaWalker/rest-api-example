package ru.test.company.repository.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, UUID> {
    List<Calendar> getCalendarsByEmployee_Id(UUID uuid);
    Calendar getCalendarByEmployeeIdAndEndIntervalDateAndEvent(UUID employee_id, LocalDateTime endIntervalDate, Event event);
}
