package com.ed;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;


public class ScriptFirst {

    private static List<LocalDate> holidays = Arrays.asList(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 5, 1)
    );


    public static Date getSendDate(){

        LocalDateTime currentDate = LocalDateTime.now();

        LocalDateTime[] sendDaysList = {
                LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 1, 18,0),
                LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 10, 18,0),
                LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 20, 18,0)
        };

        for (LocalDateTime sendDay : sendDaysList) {

            if(currentDate.isBefore(sendDay)){

                Date verificationDate = Date.valueOf(sendDay.toLocalDate());

                if (getVacCheck(verificationDate).equals(verificationDate)){
                    return Date.valueOf(sendDay.toLocalDate());
                } else {
                    return Date.valueOf(sendDay.minusDays(1).toLocalDate());
                }

            }
        }

        LocalDate dayNextMonth = currentDate.toLocalDate().with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate nextSendDay = LocalDate.of(dayNextMonth.getYear(), dayNextMonth.getMonth(), dayNextMonth.getDayOfMonth());

        return getVacCheck(Date.valueOf(nextSendDay));
    }


    /**
     * проверяет дату, является ли она рабочей.
     * если выходной - возвращает ближайший рабочий день следующий за выходными.
     * Возвращает переменную типа java.sql.Date     *
     */

    public static Date getVacCheck(Date modDate){

        LocalDate date = modDate.toLocalDate();

        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(date) ){
            return Date.valueOf(date.plusDays(1));
        }
        return modDate;


    }




}
