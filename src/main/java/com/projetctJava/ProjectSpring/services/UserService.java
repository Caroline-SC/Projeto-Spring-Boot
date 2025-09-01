package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.dto.request.UserRequest;
import com.projetctJava.ProjectSpring.dto.response.UserResponse;
import com.projetctJava.ProjectSpring.dto.response.UserWithOrdersResponse;
import com.projetctJava.ProjectSpring.exceptions.custom.DuplicateResourceException;
import com.projetctJava.ProjectSpring.exceptions.custom.InvalidParamException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    //Find all
    public List<UserResponse> findAll() {
        if (repository.findAll().isEmpty()){
            throw new ResourceNotFoundException("User");
        }
        return repository.findAll().stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
}
    //Find By id
    public UserResponse findById(Long id){

        return UserResponse.fromEntity(repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "User")));

    }
    public UserWithOrdersResponse findByIdWithOrders(Long id){

        return UserWithOrdersResponse.fromEntity(repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "User")));

    }

    //Search users
    public List<UserResponse> searchUsers(String name,
                                  String email,
                                  String number,
                                  String address,
                                  String direction){
        if(name != null) name = name.trim().toLowerCase().replace(" ","");
        if(email != null) email = email.trim();
        if(number != null) number = number.trim();
        if(address != null) address = address.trim().toLowerCase().replace(" ","");

        Sort sort = createSort(direction);

        return repository.searchUser(name,email,number,address,sort).stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }
    public UserResponse createUser(UserRequest userRequest){
        if (repository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateResourceException("Email");
        }
        if (repository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new DuplicateResourceException("PhoneNumber");
        }
        User user = new User(null,userRequest.getName(),userRequest.getEmail(),userRequest.getAddress(), userRequest.getPhoneNumber());
        repository.save(user);

        return UserResponse.fromEntity(user);
    }


    private Sort createSort(String direction) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (direction != null){
            if (direction.equalsIgnoreCase("desc")){
                sortDirection = Sort.Direction.DESC;
            }
            else if(direction.equalsIgnoreCase("asc")){}
            else{
                throw new InvalidParamException("direction","asc or desc",direction);
            }
        }

        return Sort.by(sortDirection, "name");
    }

}

