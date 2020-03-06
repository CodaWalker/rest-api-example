package ru.test.company.model.calendar;


import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SimpleData {
    public String year;
    public String month;
    public String day;

    public static LocalDateTime convertSimpleDataToLocalDateTime(SimpleData simpleData){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MM yyyy");
        String date = simpleData.day+" "+simpleData.month+" "+simpleData.year;
        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDate.parse(date,dtf).atTime(0, 0, 0);
        }
        catch (DateTimeParseException e){
            e.getMessage();
            System.out.println("Не правильно введены значения даты! Формат: d MM yyyy ");
            return null;
        }
        return localDateTime;
    }
}
