package ru.test.company.api.calendar.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.test.company.api.calendar.dto.out.CalendarDto;
import ru.test.company.model.calendar.Calendar;
import ru.test.company.model.calendar.SimpleData;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CalendarMapper {


    Calendar toEntity(CalendarDto dto);

    @Mapping(target="employee_id", source="entity.employee.id")
    @Mapping(target="employee_firstName", source="entity.employee.firstName")
    @Mapping(target="employee_lastName", source="entity.employee.lastName")
    @Mapping(target="employee_departmentId", source="entity.employee.department.id")
    @Mapping(target="event", source="entity.event")
    CalendarDto toDto(Calendar entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<CalendarDto> toDtoListFromDB(List<Calendar> entityList);


}


