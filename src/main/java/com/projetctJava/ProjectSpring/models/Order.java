package com.projetctJava.ProjectSpring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant moment;

    @Getter @Setter @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order (Long id, Instant moment, OrderStatus status){
        this.id = id;
        this.moment = moment;
        this.status = status;
    }




}
