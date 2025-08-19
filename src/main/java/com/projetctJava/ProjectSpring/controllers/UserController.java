package com.projetctJava.ProjectSpring.controllers;

import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping(value = "/search")
    public ResponseEntity<List<User>> findUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address)
    {
        List<User> list = service.findUsers(id,name,email,phoneNumber,address);
        return ResponseEntity.ok().body(list);
    }

}
