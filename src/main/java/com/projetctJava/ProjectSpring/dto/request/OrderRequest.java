package com.projetctJava.ProjectSpring.dto.request;

import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Instant moment;
    @NotNull(message= "Status is required")
    private String status;
    @NotNull(message = "Client id is required")
    private Long clientId;

    @NotEmpty(message="Orders must have at least one item")
    @Valid
    List<OrderItemRequest> items;

    public OrderRequest(Instant moment,String status, Long clientId, List<OrderItemRequest> items) {
        this.moment = moment == null ? Instant.now() : moment;
        this.status = status;
        this.clientId = clientId;
        this.items = items;
    }
}
