package com.projetctJava.ProjectSpring.controllers;

import com.projetctJava.ProjectSpring.dto.request.OrderRequest;
import com.projetctJava.ProjectSpring.dto.request.StatusUpdateRequest;
import com.projetctJava.ProjectSpring.dto.response.OrderResponse;
import com.projetctJava.ProjectSpring.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        OrderResponse obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<OrderResponse>> findOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction){

        List<OrderResponse> list = service.searchOrders(status, clientName, startDate, endDate, sortBy, direction);
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<List<OrderResponse>> findByUserId(@PathVariable Long id){
        List<OrderResponse> list = service.findByUserId(id);
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = service.createOrder(orderRequest);
        return ResponseEntity.created(URI.create("/api/orders/" + orderResponse.getId())).body(orderResponse);
    }
    @PatchMapping(value= "/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id,
                                                      @RequestBody StatusUpdateRequest request){
        OrderResponse orderResponse = service.updateStatus(id,request);
        return ResponseEntity.ok().body(orderResponse);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
