package com.projetctJava.ProjectSpring.models.enums;

import com.projetctJava.ProjectSpring.exceptions.custom.IllegalStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum OrderStatus {
    WAITING_PAYMENT(1,"WaitingPayment"),
    PAID(2,"Paid"),
    SHIPPED(3,"Shipped"),
    DELIVERED(4,"Delivered"),
    CANCELED(5,"Canceled");

    private int code;
    private String nameString;


    public static OrderStatus fromString(String text){
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equalsIgnoreCase(text) 
            || status.getNameString().equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalStatusException(text);
    }

}
