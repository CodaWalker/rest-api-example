package ru.test.company.model.calendar;


import lombok.*;
import ru.test.company.error.ErrorCustom;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleData {
    public String year;
    public String month;
    public String day;

    public static LocalDate convertSimpleDataToLocalDateTime(SimpleData simpleData) throws ErrorCustom {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = simpleData.year+"-"+simpleData.month+"-"+simpleData.day;
        LocalDate localDateTime = LocalDate.now();
        try {
            localDateTime = LocalDate.parse(date,dtf);
        }
        catch (DateTimeParseException e){
            e.getMessage();

            throw new ErrorCustom(4,"Не правильно введены значения даты! Формат: yyyy-MM-dd ");

        }
        return localDateTime;
    }

    public static SimpleData convertLocalDateTimeToSimpleData(LocalDate localDate) throws ErrorCustom {
        String month = String.valueOf(localDate.getMonthValue());
        String day = String.valueOf(localDate.getDayOfMonth());
        if(month.length() < 2){month = "0"+month; }
        if(day.length() < 2){day = "0"+day; }
        return SimpleData.builder()
                .day(String.valueOf(localDate.getDayOfMonth()))
                .month(month)
                .year(String.valueOf(localDate.getYear()))
                .build();
    }

    public static Json convertSimpleDataToJson(SimpleData simpleData) throws ErrorCustom {
        return new Json("{\"date\":\""+simpleData.year+"-"+simpleData.month+"-"+simpleData.day+"\"}");
    }

    public static Json convertLocalDataToJson(LocalDate localDate) {
        return new Json("{\"date\":\""+localDate.getYear()+"-"+localDate.getMonthValue()+"-"+localDate.getDayOfMonth()+"\"}");
    }
}
