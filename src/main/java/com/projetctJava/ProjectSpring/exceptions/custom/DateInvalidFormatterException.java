package com.projetctJava.ProjectSpring.exceptions.custom;

public class DateInvalidFormatterException extends RuntimeException {
    public DateInvalidFormatterException(String message) {
        super("Failed to convert date. Expected format \"yy-mm-yyyy\"");
    }
}
