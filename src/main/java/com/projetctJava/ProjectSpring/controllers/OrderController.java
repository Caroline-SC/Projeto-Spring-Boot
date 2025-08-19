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

    @GetMapping(value= "/status")
    public ResponseEntity<List<Order>> findByStatus(@RequestParam String status){
        List<Order> list = service.findByStatus(status);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value= "/before")
    public ResponseEntity<List<Order>> findBeforeDay(@RequestParam String day) {
            List<Order> list = service.findBeforeDay(day);
            return ResponseEntity.ok().body(list);
    }
    @GetMapping(value= "/after")
    public ResponseEntity<List<Order>> findAfterDay(@RequestParam String day) {
        List<Order> list = service.findAfterDay(day);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value= "/between")
    public ResponseEntity<List<Order>> findBetweenDays(
            @RequestParam String start,
            @RequestParam String end) {
        List<Order> list = service.findBetweenDays(start,end);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/search")
    public ResponseEntity<List<Order>> findOrders(@PathVariable(required = false) String status,
    @PathVariable(required = false) String moment){
        List<Order> list = service.findOrders(status, moment);
        return ResponseEntity.ok().body(list);

    }
    
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<List<Order>> findByUserId(@PathVariable Long id){
        List<Order> list = service.findByUserId(id);
        return ResponseEntity.ok().body(list);
    }


}
