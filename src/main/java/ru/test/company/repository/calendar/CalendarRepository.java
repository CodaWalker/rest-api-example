package ru.test.company.repository.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, UUID> {
    List<Calendar> getCalendarsByEmployee_Id(UUID uuid);

    @Query(nativeQuery = true, countQuery = "SELECT * FROM calendar WHERE employee_id = ? and event = ? and last_interval_date = ?")
    Calendar getCalendarByEmployeeIdAndEventAndEndIntervalDate(UUID employee_id, Event event, LocalDate endIntervalDate);
}
