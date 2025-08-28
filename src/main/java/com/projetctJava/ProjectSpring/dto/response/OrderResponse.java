package com.projetctJava.ProjectSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class OrderResponse {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant moment;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OrderStatus status;
    private Double total;
    private UserMinResponse client;
    private List<OrderItemResponse> items;

    private OrderResponse (Order order){
        BeanUtils.copyProperties(order,this);
        this.client = UserMinResponse.fromEntity(order.getClient());
        this.items = order.getItems().stream()
                .map(OrderItemResponse::fromEntity)
                .collect(Collectors.toList());
        this.total = order.getTotal();
    }
    public static OrderResponse fromEntity(Order order){
        return new OrderResponse(order);
    }
}
