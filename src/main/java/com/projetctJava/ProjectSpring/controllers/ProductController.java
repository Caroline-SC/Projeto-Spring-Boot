package com.projetctJava.ProjectSpring.controllers;

import com.projetctJava.ProjectSpring.dto.response.ProductResponse;
import com.projetctJava.ProjectSpring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        ProductResponse obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductResponse>> findProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction
    ){
        List<ProductResponse> list = service.searchProducts(minPrice, maxPrice, name, sortBy, direction);
        return ResponseEntity.ok().body(list);
    }


}
