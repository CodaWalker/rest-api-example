package ru.test.company.api.calendar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.action.CalendarAction;
import ru.test.company.api.calendar.dto.in.CalendarCreateDto;
import ru.test.company.api.calendar.dto.out.CalendarDto;
import ru.test.company.api.calendar.mapper.CalendarMapper;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.calendar.SimpleData;
import ru.test.company.model.employee.Event;
import ru.test.company.service.calendar.CalendarService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @ApiOperation("Получить список записей календаря сотрудника")
    @GetMapping(value = "/get/{id}")
    public List<CalendarDto> getOne(@PathVariable UUID id) {
        return calendarMapper.toDtoListFromDB(calendarService.getAllByEmployeeId(id));
    }


    @ApiOperation("Создать календарь")
    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public CalendarDto create(@RequestBody CalendarCreateDto dto) throws ErrorCustom {
        return calendarMapper.toDto(calendarAction.execute(dto));
    }

    @ApiOperation("Получить дату последнего отпуска")
    @PostMapping("/report/get-latest-holiday/{id}")
    public SimpleData getLatestHoliday(@PathVariable UUID id) throws ErrorCustom {
        return SimpleData.convertLocalDateTimeToSimpleData(
                calendarService.getCalendarByLastDateAndEvent(id, Event.ABSENTED_HOLIDAY));
    }

    @ApiOperation("Отметим посещение на сегодня")
    @PostMapping("/set-presence-at-work/{id}")
    public CalendarDto setPresence(@PathVariable UUID id) throws ErrorCustom {
        CalendarCreateDto dto = CalendarCreateDto.builder()
                .employeeId(id)
                .startIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                .endIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                .event(Event.PRESENCE_AT_WORK)
                .build();
        return calendarMapper.toDto(calendarAction.execute(dto));
    }

        @ApiOperation("Отметим прогул на сегодня")
        @PostMapping("/set-absented-at-work/{id}")
        public CalendarDto setAbsented(@PathVariable UUID id) throws ErrorCustom {
            CalendarCreateDto dto = CalendarCreateDto.builder()
                    .employeeId(id)
                    .startIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                    .endIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                    .event(Event.ABSENTED_OTHER)
                    .build();
        return calendarMapper.toDto(calendarAction.execute(dto));
    }
        @ApiOperation("Отметим отпуск на сегодня")
        @PostMapping("/set-absented-holiday-at-work/{id}")
        public CalendarDto setAbsentedHoliday(@PathVariable UUID id) throws ErrorCustom {
            CalendarCreateDto dto = CalendarCreateDto.builder()
                    .employeeId(id)
                    .startIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                    .endIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                    .event(Event.ABSENTED_OTHER)
                    .build();
            return calendarMapper.toDto(calendarAction.execute(dto));
        }
        @ApiOperation("Отметим больничный на сегодня")
        @PostMapping("/set-absented-medical-at-work/{id}")
        public CalendarDto setAbsentedMedical(@PathVariable UUID id) throws ErrorCustom {
            CalendarCreateDto dto = CalendarCreateDto.builder()
                    .employeeId(id)
                    .startIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                    .endIntervalDate(SimpleData.convertLocalDateTimeToSimpleData(LocalDate.now()))
                    .event(Event.ABSENTED_OTHER)
                    .build();
            return calendarMapper.toDto(calendarAction.execute(dto));
    }
}
