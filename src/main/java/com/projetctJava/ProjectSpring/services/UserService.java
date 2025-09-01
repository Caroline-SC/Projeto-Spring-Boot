package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.dto.request.UserCreateRequest;
import com.projetctJava.ProjectSpring.dto.request.UserUpdateRequest;
import com.projetctJava.ProjectSpring.dto.response.UserResponse;
import com.projetctJava.ProjectSpring.dto.response.UserWithOrdersResponse;
import com.projetctJava.ProjectSpring.exceptions.custom.DuplicateResourceException;
import com.projetctJava.ProjectSpring.exceptions.custom.InvalidParamException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceHasAssociationsException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.repositories.OrderRepository;
import com.projetctJava.ProjectSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private OrderRepository orderRepository;

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
    @Transactional
    public UserResponse createUser(UserCreateRequest userCreateRequest){
        if (repository.existsByEmail(userCreateRequest.getEmail())) {
            throw new DuplicateResourceException("Email");
        }
        if (repository.existsByPhoneNumber(userCreateRequest.getPhoneNumber())) {
            throw new DuplicateResourceException("PhoneNumber");
        }
        User user = new User(null, userCreateRequest.getName(), userCreateRequest.getEmail(), userCreateRequest.getAddress(), userCreateRequest.getPhoneNumber());
        repository.save(user);

        return UserResponse.fromEntity(user);
    }
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "User"));

        if (userUpdateRequest.getEmail() != null && !userUpdateRequest.getEmail().isBlank()) {
            if (!user.getEmail().equals(userUpdateRequest.getEmail())) {
                if (repository.existsByEmail(userUpdateRequest.getEmail())) {
                    throw new DuplicateResourceException("Email");
                }
                user.setEmail(userUpdateRequest.getEmail());
            }
        }

        if (userUpdateRequest.getPhoneNumber() != null && !userUpdateRequest.getPhoneNumber().isBlank()) {
            if (!user.getPhoneNumber().equals(userUpdateRequest.getPhoneNumber())) {
                if (repository.existsByPhoneNumber(userUpdateRequest.getPhoneNumber())) {
                    throw new DuplicateResourceException("PhoneNumber");
                }
                user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
            }
        }
        if (userUpdateRequest.getAddress() != null && !userUpdateRequest.getAddress().isBlank()){
            user.setAddress(userUpdateRequest.getAddress());
        }

        if (userUpdateRequest.getName() != null && !userUpdateRequest.getName().isBlank()) {
            user.setName(userUpdateRequest.getName());
        }

        User updatedUser = repository.save(user);
        return UserResponse.fromEntity(updatedUser);
    }
    @Transactional
    public void deleteById(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "User"));
        if (orderRepository.existsByClientId(id)){
            throw new ResourceHasAssociationsException("user",id);
        }
            repository.deleteUserById(id);

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

