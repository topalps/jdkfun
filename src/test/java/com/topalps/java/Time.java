package com.topalps.java;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 休清 on 2017/6/25.
 *
 * @author 休清
 * @date 2017/06/25
 */
public class Time {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void firstDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfThisMonth.format(formatter));
    }

    @Test
    public void secondDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println(secondDayOfThisMonth);
    }

    @Test
    public void lastDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDateOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDateOfThisMonth.format(formatter));
    }

    @Test
    public void nextDay() {
        LocalDate today = LocalDate.now();
        LocalDate nextDay = today.plusDays(1);
        System.out.println(nextDay.format(formatter));
    }

    @Test
    public void firstMondayOf2015() {
        LocalDate firstMonthOf2015 = LocalDate.of(2015,1,1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstMonthOf2015.format(formatter));
    }

    @Test
    public void dayGap() {
        LocalDate start = LocalDate.of(2015,2,25);
        LocalDate end = LocalDate.of(2015,3,1);
        Period period = Period.between(start, end);
        System.out.println(period.getDays());
    }

    @Test
    public void dayiter() {
        LocalDate start = LocalDate.of(2017,1,1);
        LocalDate end = LocalDate.of(2017,2,1);
        List<String> days = Stream.iterate(start, localDate -> localDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                .map(LocalDate::toString)
                .collect(Collectors.toList());

        days.forEach(t -> System.out.println(t));
    }

    @Test
    public void zonedtime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime.format(formatter) + " "  + zonedDateTime.toInstant().toEpochMilli());

        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/London")).format(formatter));

        ZonedDateTime time2 = zonedDateTime.withZoneSameLocal(ZoneId.of("Europe/London"));
        System.out.println(zonedDateTime.withZoneSameLocal(ZoneId.of("Europe/London")).format(formatter) + " " + time2.toInstant().toEpochMilli());


    }
}
