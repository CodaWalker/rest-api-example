package ru.test.company.model.calendar;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CALENDAR")
public class Calendar extends BaseEntity {
    /** Сотрудник **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    /** Событие связанное с пребыванием или отстутсвием сотрудника */
    @Builder.Default
    @Column(name = "EVENT", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PRESENCE_AT_WORK'")
    @Enumerated(EnumType.STRING)
    private Event event;

    /** Первый рабочий день **/
    @Column(name = "FIRST_INTERVAL_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate startIntervalDate;

    /** День увольнения **/
    @Column(name = "LAST_INTERVAL_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate endIntervalDate;
}
