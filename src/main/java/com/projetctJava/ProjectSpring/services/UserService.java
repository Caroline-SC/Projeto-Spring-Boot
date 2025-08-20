package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        if (repository.findAll().isEmpty()){
            throw new ResourceNotFoundException("User");
        }
        return repository.findAll();
}
    public User findById(Long id){
        return repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "User"));

    }
    public List<User> findUsers(Long id,String name,String email,String phone,String address){
        List<Specification<User>> spec = new ArrayList<>();

        if (id != null){
            spec.add((root,query,cb) ->
                    cb.equal(root.get("id"),id));
        }
        if (name != null){
            String nameFormated = "%" + name.trim().toLowerCase() + "%";
            spec.add((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), nameFormated));
        }
        if (email != null){
            spec.add((root,query,cb) ->
                    cb.equal(root.get("email"),email));
        }
        if (phone != null){
            spec.add((root,query,cb) ->
                    cb.equal(root.get("phoneNumber"),phone));
        }
        if (address != null){
            String addressFormated = "%" + address.trim().toLowerCase() + "%";
            spec.add((root,query,cb) ->
                    cb.like(cb.lower(root.get("address")),addressFormated));
        }

        return repository.findAll(spec.stream()
                .reduce((root, query, cb) ->
                        cb.conjunction(), (spec1, spec2) -> spec1.and(spec2) ));
    }

}

