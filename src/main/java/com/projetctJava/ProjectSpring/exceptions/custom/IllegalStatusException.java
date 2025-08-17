package com.projetctJava.ProjectSpring.exceptions.custom;

import com.projetctJava.ProjectSpring.models.enums.OrderStatus;

import java.util.Arrays;

public class IllegalStatusException extends RuntimeException {
    public IllegalStatusException(String status) {
        super("Invalid '" + status + "' status. Valid values : "+ Arrays.toString(OrderStatus.values()));
    }
}
