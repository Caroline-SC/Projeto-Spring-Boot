package com.projetctJava.ProjectSpring.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),//atual
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

}
