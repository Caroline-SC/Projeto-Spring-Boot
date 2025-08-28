package com.projetctJava.ProjectSpring.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetctJava.ProjectSpring.models.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class UserWithOrdersResponse extends UserResponse{

    @JsonIgnore
    private Integer ordersRequests;

    List<OrderResponse> orders;

    private UserWithOrdersResponse(User user) {
        BeanUtils.copyProperties(user,this);
        this.orders = user.getOrders().stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public static UserWithOrdersResponse fromEntity(User user){
        return new UserWithOrdersResponse(user);
    }
}
