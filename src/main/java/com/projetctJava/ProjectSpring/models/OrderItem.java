package com.projetctJava.ProjectSpring.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class OrderItem {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double currentPrice;

    @Transient
    private Product product;

    public OrderItem(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.productId = product.getId();
        this.productName = product.getName();
        this.currentPrice = product.getPrice();
    }

    public Double getsubTotal(){
        return quantity*currentPrice;
    }
}
