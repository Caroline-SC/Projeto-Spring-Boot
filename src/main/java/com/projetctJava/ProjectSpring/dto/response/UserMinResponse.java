package com.projetctJava.ProjectSpring.dto.response;

import com.projetctJava.ProjectSpring.models.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class UserMinResponse {
    private Long id;
    private String name;
    private String email;

    private UserMinResponse(User user){
        BeanUtils.copyProperties(user,this);
    }
    public static UserMinResponse fromEntity(User user){
        return new UserMinResponse(user);
    }
    
}
