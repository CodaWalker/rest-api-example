package ru.test.company.model.position;

import lombok.*;
import ru.test.company.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POSITION")
public class Position extends BaseEntity {

    /** Название **/
    @Column(name = "NAME")
    private String name;

}
