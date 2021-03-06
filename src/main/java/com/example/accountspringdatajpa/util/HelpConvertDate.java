package com.example.accountspringdatajpa.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HelpConvertDate {
    public static LocalDate toLocalDateTime(String dateStr, boolean startDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate getDate = LocalDate.parse(dateStr, dateFormatter);
        if (startDate) {
            dateStr += " 00:00:00";
            LocalDate startDateTime = LocalDate.parse(dateStr, dateTimeFormatter);
            return startDateTime;
        }else {
            dateStr += " 23:59:59";
            LocalDate endDateTime = LocalDate.parse(dateStr, dateTimeFormatter);
            return endDateTime;
        }
    }
}