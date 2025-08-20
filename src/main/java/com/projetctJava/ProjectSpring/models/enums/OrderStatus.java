package com.projetctJava.ProjectSpring.models.enums;

import com.projetctJava.ProjectSpring.exceptions.custom.IllegalStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;


    public static OrderStatus fromString(String text){
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalStatusException(text);
    }
    public static String formatStatus(String text){
        if (text.equalsIgnoreCase("waitingpayment")) text = "waiting_payment";
        return text.trim();
    }


}
