package com.projetctJava.ProjectSpring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
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



    public Order (Long id, Instant moment, OrderStatus status, User client){
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.statusCode = this.status.getCode();
    }

    @PrePersist
    @PreUpdate
    public void updateStatusCode() {
        if (this.status != null) {
            this.statusCode = this.status.getCode();
        }}



}
