package ru.test.company.repository.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(countQuery = "SELECT c FROM Calendar c WHERE c.employee.id = :eId AND c.event = :event and c.endIntervalDate = :end")
    Calendar getCalendarByEmployeeIdAndEventAndEndIntervalDate(@Param("eId") UUID employee_id,@Param("event")  Event event,@Param("end")  LocalDate endIntervalDate);

    @Query(countQuery = "SELECT c FROM Calendar c WHERE c.employee.id = :eId AND c.event = :event ORDER BY c.endIntervalDate  DESC")
    Calendar findTopCalendarByEmployeeIdAndEventOrderByEndIntervalDateDesc(UUID employee_id, Event event);

}
