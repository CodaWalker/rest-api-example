package ru.test.company.api.calendar;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.action.GetCompanyAction;
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
    private final GetCompanyAction getCompanyAction;
    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

    @Autowired
    public CalendarInternalController(CalendarService calendarService, GetCompanyAction getCompanyAction, CalendarService calendarService1, CalendarMapper calendarMapper) {
        this.getCompanyAction = getCompanyAction;
        this.calendarService = calendarService1;
        this.calendarMapper = calendarMapper;
    }


    @ApiOperation("Получить список записей календаря")
    @GetMapping(value = "/all")
    public List<CalendarDto> getAll() {
        return calendarMapper.toDtoListFromDB(getCompanyAction.getAll());
    }


    @ApiOperation("Отметить отпуск для сотрудника на текущий день")
    @PutMapping("/create/set-absented-holiday")
    @ResponseStatus(CREATED)
    public CalendarDto setAbsentedHolidayIsDay(@RequestBody CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return calendarMapper.toDto(calendarService.setAbsentedHolidayEmployee(calendarCreateArgument));
    }

    @ApiOperation("Отметить больничный для сотрудника на текущий день")
    @PutMapping("/create/set-absented-medical")
    @ResponseStatus(CREATED)
    public CalendarDto setAbsentedMedicalIsDay(@RequestBody CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return calendarMapper.toDto(calendarService.setAbsentedMedicalEmployee(calendarCreateArgument));
    }

    @ApiOperation("Отметить прогул для сотрудника на текущий день")
    @PutMapping("/create/set-absented-other")
    @ResponseStatus(CREATED)
    public CalendarDto setAbsentedOtherIsDay(@RequestBody CalendarCreateArgument calendarCreateArgument) throws ErrorCustom {
        return calendarMapper.toDto(calendarService.setAbsentedOtherEmployee(calendarCreateArgument));
    }

}
