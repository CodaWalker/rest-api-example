package ru.test.company.model.employee;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.department.Department;
import ru.test.company.model.position.Position;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEES")
public class Employee extends BaseEntity {

    public void setID(UUID uuid) {
        super.setId(uuid);
    }

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

    /** Должность **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    /** Пристуствие на работе */
    @Column(name = "PRESENCE_AT_WORK")
    private Boolean presenceAtWork;

    /** Инфиализация */
    @PrePersist
    private void setInitialValues() {
        presenceAtWork = true;
        firstWorkingDate = LocalDateTime.now();
    }
}
