package com.projetctJava.ProjectSpring.controllers;

import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Order>> findOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction){

        List<Order> list = service.searchOrders(status, clientName, startDate, endDate, sortBy, direction);
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<List<Order>> findByUserId(@PathVariable Long id){
        List<Order> list = service.findByUserId(id);
        return ResponseEntity.ok().body(list);
    }


}
