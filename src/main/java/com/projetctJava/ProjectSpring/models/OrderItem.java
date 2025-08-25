package com.projetctJava.ProjectSpring.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class OrderItem {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private Double currentPrice;

    public Double subTotal(){
        return quantity*currentPrice;
    }
}
