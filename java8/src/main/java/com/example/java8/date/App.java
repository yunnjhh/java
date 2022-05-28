package com.example.java8.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

    public static void main(String[] args) throws InterruptedException {

        // Date 관련 기존 Library : Date, Calendar, SimpleDateFormat
        useDateAPI();
        useCalendarAPI();
        useInstantAPI();
        useLocalDateTime();
        usePeriodAPI();
        useDurationAPI();
        formattingAndParsing();
        supportLegacyAPI();
    }

    private static void supportLegacyAPI() {
        System.out.println("=====supportLegacyAPI=====");

        // date <-> instant
        Date date = new Date();
        Instant instant = Instant.now();
        System.out.println("date: " + date);
        System.out.println("instant: " + instant);

        Date instantToDate = Date.from(instant);
        System.out.println("instantToDate: " + instantToDate);

        // GregorianCalendar <->  ZonedDateTime
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime localDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        ZonedDateTime zonedDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());

        System.out.println("localDateTime: " + localDateTime);
        System.out.println("zonedDateTime: " + zonedDateTime);

        GregorianCalendar from = GregorianCalendar.from(zonedDateTime);
        System.out.println("ZonedDateTime To GregorianCalendar: " + from.getTime());

        // TimeZone <-> ZoneId
        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);

        System.out.println("ZoneId: " + zoneId);
        System.out.println("TimeZone: " + timeZone.getID());

    }

    private static void formattingAndParsing() {
        System.out.println("=====Formatting/Parsing=====");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime-now: " + now);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy~MM~dd");
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(now.format(dateTimeFormatter));

        LocalDate parseTomorrow = LocalDate.parse("2022~05~29", dateTimeFormatter);
        System.out.println(parseTomorrow);
    }

    private static void useDurationAPI() {
        System.out.println("=====useDurationAPI=====");
        Instant nowDuration = Instant.now();
        Duration between = Duration.between(nowDuration, nowDuration.plus(10, ChronoUnit.SECONDS));
        System.out.println("Duration: " + between.getSeconds());
    }

    private static void usePeriodAPI() {
        System.out.println("=====usePeriodAPI=====");

        LocalDate today = LocalDate.now();
        LocalDate parisOlympiad = LocalDate.of(2024, Month.JULY, 26);

        Period period = Period.between(today, parisOlympiad);
        System.out.println("period: " + period.getYears());

        Period until = today.until(parisOlympiad);
        System.out.println("until: " + until.getYears());

        System.out.println(period.get(ChronoUnit.DAYS));
    }

    private static void useLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before1Day = LocalDateTime.of(2022, Month.MAY, now.getDayOfMonth() - 1,
                        now.getHour(), now.getMinute(), now.getSecond());

        System.out.println("[LocalDateTime] now: " + now);
        System.out.println("[LocalDateTime] before1Day: " + before1Day);

        System.out.println("요일: " + now.getDayOfWeek() + ", " + before1Day.getDayOfWeek());
        System.out.println();

        ZonedDateTime nowINLA = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println("LA Zone Time : " + nowINLA.getZone() + " / " + nowINLA);

        Instant instant = Instant.now();
        ZonedDateTime nowInLAInstant = instant.atZone(ZoneId.of("America/Los_Angeles"));
        System.out.println("LA Zone Time(instant) : " + nowInLAInstant.getZone() + " / " + nowInLAInstant);

        LocalDateTime localDateTimeInLA = nowInLAInstant.toLocalDateTime();
        Instant instantInLA = nowInLAInstant.toInstant();
        System.out.println(localDateTimeInLA);
        System.out.println(instantInLA);
    }

    private static void useInstantAPI() {

        System.out.println("=====useInstantAPI=====");

        Instant instant = Instant.now();
        System.out.println("now: " + instant); // 기준시 UTC, GMT

        ZoneId localZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(localZone);
        System.out.println("local: " + localZone + " / " + zonedDateTime);

        ZoneId utcZone = ZoneId.of("UTC");
        ZonedDateTime zonedDateTimeUTC = instant.atZone(utcZone);
        System.out.println("utc: " + utcZone + " / " + zonedDateTimeUTC);
        System.out.println();

    }

    private static void useDateAPI() throws InterruptedException {

        System.out.println("=====useDateAPI=====");

        Date date = new Date();
        System.out.println("Date: " + date + ", timeStamp: " + date.getTime());

        // Date : mutable 함
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println("after3Seconds: " + after3Seconds);
        date.setTime(after3Seconds.getTime());
        System.out.println("date: " + date);
        System.out.println();
    }

    private static void useCalendarAPI() {

        System.out.println("=====useCalendarAPI=====");

        Calendar calendar = new GregorianCalendar(2022, 5, 25);
        Calendar calendarMonth = new GregorianCalendar(2022, Calendar.MAY, 25);

        System.out.println(calendar.getTime());
        System.out.println(calendarMonth.getTime());
    }


}
