package com.example.java8.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

    public static void main(String[] args) throws InterruptedException {

        // Date 관련 기존 Library : Date, Calendar, SimpleDateFormat
        useDateAPI();
        useCalendarAPI();

    }

    private static void useDateAPI() throws InterruptedException {
        Date date = new Date();
        System.out.println("Date: " + date + ", timeStamp: " + date.getTime());

        // Date : mutable 함
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println("after3Seconds: " + after3Seconds);
        date.setTime(after3Seconds.getTime());
        System.out.println("date: " + date);
    }

    private static void useCalendarAPI() {
        Calendar calendar = new GregorianCalendar(2022, 5, 25);
        Calendar calendarMonth = new GregorianCalendar(2022, Calendar.MAY, 25);

        System.out.println(calendar.getTime());
        System.out.println(calendarMonth.getTime());
    }


}
