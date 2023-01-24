package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final LocalDate NOW = LocalDate.of(2023, 01, 27);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d::MMM::yyyy");

    public static LocalDate of(int year, Month month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static String format(LocalDate date) {
        if (date == null) return "";
        return date.equals(NOW) ? "Сейчас" : date.format(DATE_FORMATTER);
    }

    public static LocalDate parse(String date) {
        if ("Сейчас".equals(date)) return NOW;
        YearMonth yearMonth = YearMonth.parse(date, DATE_FORMATTER);
        return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
    }
}