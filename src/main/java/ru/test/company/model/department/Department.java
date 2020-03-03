package ru.test.company.model.department;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.test.company.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {
    /** Название **/
    @Column(name = "NAME")
    private String name;

    /** Первый рабочий день **/
    @Column(name = "FIRST_WORKING_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime firstWorkingDate;

    /** День увольнения **/
    @Column(name = "LAST_WORKING_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastWorkingDate;

    /** Инфиализация */
    @PrePersist
    private void setInitialValues() {
        firstWorkingDate = LocalDateTime.now();
    }

}
