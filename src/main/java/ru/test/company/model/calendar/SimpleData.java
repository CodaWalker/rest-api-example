package ru.test.company.model.calendar;


import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleData {
    public String year;
    public String month;
    public String day;

    public static LocalDateTime convertSimpleDataToLocalDateTime(SimpleData simpleData){

        DateTimeFormatter dtf =
                DateTimeFormatter.ofPattern("d MM yyyy");
        String date = simpleData.day+" "+simpleData.month+" "+simpleData.year;

        LocalDate localDate = LocalDate.parse(date,dtf);
        return localDate.atTime(0, 0, 0);
    }
}
