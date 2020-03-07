package ru.test.company.api.calendar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.action.CalendarAction;
import ru.test.company.api.calendar.dto.out.CalendarDto;
import ru.test.company.api.calendar.mapper.CalendarMapper;
import ru.test.company.error.ErrorCustom;
import ru.test.company.service.calendar.CalendarService;
import ru.test.company.service.calendar.argument.CalendarCreateArgument;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/calendar")
@Api("Внутренний контроллер календаря")
public class CalendarInternalController {
    private final CalendarAction calendarAction;
    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

    @Autowired
    public CalendarInternalController(CalendarService calendarService, CalendarAction calendarAction, CalendarService calendarService1, CalendarAction calendarAction1, CalendarMapper calendarMapper) {
        this.calendarService = calendarService;
        this.calendarAction = calendarAction1;
        this.calendarMapper = calendarMapper;
    }


    @ApiOperation("Получить список записей календаря")
    @GetMapping(value = "/all")
    public List<CalendarDto> getAll() {
        return calendarMapper.toDtoListFromDB(calendarService.getAll());
    }


    @ApiOperation("Создать календарь")
    @PutMapping("/create")
    @ResponseStatus(CREATED)
    public CalendarDto setAbsentedHolidayIsDay(@RequestBody CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return calendarMapper.toDto(calendarAction.execute(calendarCreateArgument));
    }

}
