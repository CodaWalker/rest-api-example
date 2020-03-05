package ru.test.company.api.calendar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.api.calendar.dto.in.CalendarCreateDto;
import ru.test.company.api.calendar.dto.out.CalendarDto;
import ru.test.company.api.calendar.mapper.CalendarMapper;
import ru.test.company.api.department.dto.in.DepartmentCreateDto;
import ru.test.company.api.department.dto.out.DepartmentDto;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.department.Department;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;
import ru.test.company.service.department.argument.DepartmentCreateArgument;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/calendar")
@Api("Внутренний контроллер календаря")
public class CalendarInternalController {
    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

    @Autowired
    public CalendarInternalController(CalendarService calendarService, CalendarMapper calendarMapper) {
        this.calendarService = calendarService;
        this.calendarMapper = calendarMapper;
    }


    @ApiOperation("Получить список записей календаря")
    @GetMapping(value = "/all")
    public List<CalendarDto> getAll() {
        return calendarMapper.toDtoListFromDB(calendarService.getAll());
    }

    @ApiOperation("Создать календарную запись")
    @PostMapping(value = "/create")
    @ResponseStatus(CREATED)
    public CalendarDto create(@RequestBody CalendarCreateDto dto) {
            return calendarMapper.toDto(
                    calendarService.createCalendar(CalendarCreateArgument.builder()
                            .employeeId(dto.getEmployee_id())
                            .event(dto.getEvent())
                            .startIntervalDate(SimpleData.convertSimpleDataToLocalDateTime(dto.getStartIntervalDate()))
                            .endIntervalDate(SimpleData.convertSimpleDataToLocalDateTime(dto.getEndIntervalDate()))
                            .build())
            );
    }
}
