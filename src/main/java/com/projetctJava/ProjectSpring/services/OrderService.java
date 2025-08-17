package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import com.projetctJava.ProjectSpring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private UserService userService;

    public List<Order> findAll() {
        List<Order> o = repository.findAll();
        if (o.isEmpty()){
            throw new ResourceNotFoundException("Orders");
        }
        return o;
    }
    public Order findById(Long id){
        return repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Order"));
    }

    public List<Order> findByStatus(String statusstr){
    OrderStatus status = OrderStatus.fromString(statusstr);
    return  repository.findByStatus(status);
    }

    public List<Order> findByUserId(Long id){
        List<Order> o = repository.findByClientId(id);

        //Check if user id exists else throw an exception
        userService.findById(id);

        if (o.isEmpty()){
            throw new ResourceNotFoundException(id, "Orders associated with client");
        }
        return repository.findByClientId(id);
    }


}
