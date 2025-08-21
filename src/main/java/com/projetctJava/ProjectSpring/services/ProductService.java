package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Product;
import com.projetctJava.ProjectSpring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    //Find all
    public List<Product> findAll() {
        List<Product> o = repository.findAll();
        if (o.isEmpty()){
            throw new ResourceNotFoundException("Products");
        }
        return o;
    }
    //Find by id
    public Product findById(Long id){
        return repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Product"));
    }
}
