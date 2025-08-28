package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.dto.response.OrderResponse;
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
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private UserService userService;

    //Find all
    public List<OrderResponse> findAll() {
        if (repository.findAll().isEmpty()){
            throw new ResourceNotFoundException("Orders");
        }
        return repository.findAll().stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
    //Find by id
    public OrderResponse findById(Long id){
        return OrderResponse.fromEntity(repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Order")));
    }

    //Find by user id
    public List<OrderResponse> findByUserId(Long id){
        //Check if user id exists else throw an exception
        userService.findById(id);

        if (repository.findByClientId(id).isEmpty()){
            throw new ResourceNotFoundException(id, "Orders associated with client");
        }
        return repository.findByClientId(id).stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
    public List<OrderResponse> searchOrders(String status,
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

        return repository.searchOrder(orderStatus,clientName,startDayInstant,endDayInstant,sort).stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
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
