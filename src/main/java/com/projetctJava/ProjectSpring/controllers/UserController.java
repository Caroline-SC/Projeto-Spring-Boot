package com.projetctJava.ProjectSpring.controllers;

import com.projetctJava.ProjectSpring.dto.request.UserCreateRequest;
import com.projetctJava.ProjectSpring.dto.request.UserUpdateRequest;
import com.projetctJava.ProjectSpring.dto.response.UserResponse;
import com.projetctJava.ProjectSpring.dto.response.UserWithOrdersResponse;
import com.projetctJava.ProjectSpring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        UserResponse obj = service.findById(id);
        return ResponseEntity.ok().body(obj);}

    @GetMapping(value = {"/{id}/orders"})
    public ResponseEntity<UserWithOrdersResponse> findByIdWithOrders(@PathVariable Long id){
        UserWithOrdersResponse obj = service.findByIdWithOrders(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<UserResponse>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String direction)
    {
        List<UserResponse> list = service.searchUsers(name,email,phoneNumber,address,direction);
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest){

        UserResponse userResponse = service.createUser(userCreateRequest);
        return ResponseEntity.created(URI.create("/api/users/" + userResponse.getId())).body(userResponse);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request) {

        UserResponse userResponse = service.updateUser(id, request);
        return ResponseEntity.ok().body(userResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
