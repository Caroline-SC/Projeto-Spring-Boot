package com.projetctJava.ProjectSpring.controllers;

import com.projetctJava.ProjectSpring.models.Product;
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
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Product>> findLessThanPrice(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction
    ){
        List<Product> list = service.searchProducts(minPrice, maxPrice, name, sortBy, direction);
        return ResponseEntity.ok().body(list);
    }


}
