package ru.test.company.service.department.argument;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DepartmentSearchArgument {

    private String name;
    private LocalDateTime lastWorkingDate;
    private LocalDateTime firstWorkingDate;
    private Integer department_id;
}

