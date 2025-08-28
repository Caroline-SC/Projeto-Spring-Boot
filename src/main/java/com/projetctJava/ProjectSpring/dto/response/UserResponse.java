package com.projetctJava.ProjectSpring.dto.response;


import com.projetctJava.ProjectSpring.models.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private Integer ordersRequests;

    protected UserResponse(User user){
        BeanUtils.copyProperties(user,this);
        this.ordersRequests = user.getOrders().size();
    }

    public static UserResponse fromEntity(User user){
        return new UserResponse(user);
    }
}

