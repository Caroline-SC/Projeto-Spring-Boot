package com.projetctJava.ProjectSpring.util;

import com.projetctJava.ProjectSpring.exceptions.custom.DateInvalidFormatterException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Instant atStartOfDay(String date){
        try{
        LocalDate localDate = LocalDate.parse(date,dtf);
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();}

        catch(DateTimeParseException e){
            throw new DateInvalidFormatterException(date);
        }
    }
    public static Instant atEndOfDay(String date){
        try{
        LocalDate localDate = LocalDate.parse(date,dtf);
        return localDate.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();}

        catch(DateTimeParseException e){
            throw new DateInvalidFormatterException(date);
        }
    }
}
