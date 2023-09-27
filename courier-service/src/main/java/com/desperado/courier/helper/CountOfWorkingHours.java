package com.desperado.courier.helper;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.desperado.courier.entities.courier.Courier;

public class CountOfWorkingHours {


    public static long getWorkingHours(Courier courier) {
        return courier.getHours().stream()
                .mapToLong(s -> {
                    String[] parseHours = s.split("-");
                    LocalTime start = LocalTime.parse(parseHours[0]);
                    LocalTime end = LocalTime.parse(parseHours[1]);
                    return start.until(end, ChronoUnit.HOURS);
                }).sum();
    }

    public static long getHoursBetweenTwoDates(Courier courier, String startDate, String endDate) {
        return CountOfWorkingHours.getWorkingHours(courier) *
                LocalDate.from(LocalDate.parse(startDate))
                        .until(LocalDate.parse(endDate),
                                ChronoUnit.DAYS);
    }

}
