package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.exceptions.custom.InvalidParamException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    //Find all
    public List<User> findAll() {
        if (repository.findAll().isEmpty()){
            throw new ResourceNotFoundException("User");
        }
        return repository.findAll();
}
    //Find By id
    public User findById(Long id){
        return repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "User"));

    }

    //Search users
    public List<User> searchUsers(String name,
                                  String email,
                                  String number,
                                  String address,
                                  String direction){
        if(name != null) name = name.trim().toLowerCase().replace(" ","");
        if(email != null) email = email.trim();
        if(number != null) number = number.trim();
        if(address != null) address = address.trim().toLowerCase().replace(" ","");

        Sort sort = createSort(direction);

        return repository.searchUser(name,email,number,address,sort);
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

