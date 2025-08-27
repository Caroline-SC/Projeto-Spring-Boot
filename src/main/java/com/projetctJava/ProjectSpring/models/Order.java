package com.projetctJava.ProjectSpring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant moment;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    //Not in future DTO
    @JsonIgnore
    @Column(name = "status_code")
    private Integer statusCode;

    @ElementCollection
    @CollectionTable(name = "order_items",joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items = new ArrayList<>();

    public Order (Long id, Instant moment, OrderStatus status, User client){
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.statusCode = this.status.getCode();
    }

    public void addItems(List<OrderItem> items){
        for(OrderItem item : items) this.items.add(item);
    }
    public void addItem(OrderItem item){
        items.add(item);
    }

    public Double getTotal(){
        return items.stream()
                .map(OrderItem::getsubTotal)
                .reduce(0.0,(x,y)-> x+y);
    }
}
