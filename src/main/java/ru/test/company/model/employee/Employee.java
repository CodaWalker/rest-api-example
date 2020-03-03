package ru.test.company.model.employee;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.department.Department;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEES")
public class Employee extends BaseEntity {

    /** Имя */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /** Фамилия */
    @Column(name = "LAST_NAME")
    private String lastName;

    /** Первый рабочий день **/
    @Column(name = "FIRST_WORKING_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime firstWorkingDate;

    /** День увольнения **/
    @Column(name = "LAST_WORKING_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastWorkingDate;

    /** Отдел **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    /** Событие связанное с пребыванием или отстутсвием сотрудника */
    @Builder.Default
    @Column(name = "EVENT", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PRESENCE_AT_WORK'")
    @Enumerated(EnumType.STRING)
    private Event event;

    /** Инфиализация */
    @PrePersist
    private void setInitialValues() {
       // firstWorkingDate = LocalDateTime.now();
    }
}
