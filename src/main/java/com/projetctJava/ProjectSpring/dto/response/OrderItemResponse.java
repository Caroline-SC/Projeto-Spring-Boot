package com.projetctJava.ProjectSpring.dto.response;

import com.projetctJava.ProjectSpring.models.OrderItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderItemResponse {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double currentPrice;
    private Double subTotal;

    private OrderItemResponse(OrderItem orderItem){
        BeanUtils.copyProperties(orderItem,this);
        this.subTotal = orderItem.getsubTotal();
    }
    public static OrderItemResponse fromEntity(OrderItem orderItem){
        return new OrderItemResponse(orderItem);
    }
}
