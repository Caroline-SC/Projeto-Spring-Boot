package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import com.projetctJava.ProjectSpring.repositories.OrderRepository;
import com.projetctJava.ProjectSpring.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private UserService userService;

    //Find all
    public List<Order> findAll() {
        List<Order> o = repository.findAll();
        if (o.isEmpty()){
            throw new ResourceNotFoundException("Orders");
        }
        return o;
    }
    //Find by id
    public Order findById(Long id){
        return repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Order"));
    }

    //Find by status
    public List<Order> findByStatus(String statusStr){
    OrderStatus status = OrderStatus.fromString(statusStr);
    return  repository.findByStatus(status);
    }

    //Find by moment
    public List<Order> findBeforeDay(String formatedDay){
    Instant moment = DateUtil.atStartOfDay(formatedDay);
    return repository.findByMomentBefore(moment);
    }
    public List<Order> findAfterDay(String formatedDay){
    Instant moment = DateUtil.atStartOfDay(formatedDay);
    return repository.findByMomentAfter(moment);
    }
    public List<Order> findBetweenDays(String startFormatedDay, String endFormatedDay){
    Instant startMoment = DateUtil.atStartOfDay(startFormatedDay);
    Instant endMoment = DateUtil.atStartOfDay(endFormatedDay);
    return repository.findByMomentBetween(startMoment,endMoment);
    }

    //Find by user id
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
