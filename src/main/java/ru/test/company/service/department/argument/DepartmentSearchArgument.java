package ru.test.company.service.department.argument;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DepartmentSearchArgument {
    private final String name;
    private final LocalDateTime lastWorkingDate;
    private final LocalDateTime firstWorkingDate;
    private final Integer department_id;
}

