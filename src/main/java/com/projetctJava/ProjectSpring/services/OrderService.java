package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.exceptions.custom.InvalidParamException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import com.projetctJava.ProjectSpring.repositories.OrderRepository;
import com.projetctJava.ProjectSpring.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<Order> searchOrders(String status,
                                    String clientName,
                                    String startDate,
                                    String endDate,
                                    String sortBy,
                                    String direction){
        Instant startDayInstant = null;
        Instant endDayInstant = null;
        OrderStatus orderStatus = null;
        if (status != null){
            status  = OrderStatus.formatStatus(status);
            orderStatus = OrderStatus.fromString(status);
        }
        if(clientName != null) clientName = clientName.trim().toLowerCase().replace(" ","");
        if(startDate != null) startDayInstant = DateUtil.atStartOfDay(startDate);
        if(endDate != null) endDayInstant = DateUtil.atStartOfDay(endDate);

        Sort sort = createSort(sortBy, direction);

        return repository.searchOrder(orderStatus,clientName,startDayInstant,endDayInstant,sort);
    }

    private Sort createSort(String sortBy, String direction) {
        if (sortBy == null || sortBy.isEmpty()){
            sortBy = "moment";}
        else{
            if (sortBy.equalsIgnoreCase("status")) sortBy = "statusCode";
            else if(!sortBy.equalsIgnoreCase("status") && !sortBy.equalsIgnoreCase("moment")){
                throw new InvalidParamException("sortBy","status or moment",sortBy);
            }}
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

        return Sort.by(sortDirection, sortBy);
    }
}
