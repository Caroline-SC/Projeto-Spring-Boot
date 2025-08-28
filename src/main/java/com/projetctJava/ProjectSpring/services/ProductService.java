package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.dto.response.OrderResponse;
import com.projetctJava.ProjectSpring.dto.response.ProductResponse;
import com.projetctJava.ProjectSpring.exceptions.custom.InvalidParamException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Product;
import com.projetctJava.ProjectSpring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    //Find all
    public List<ProductResponse> findAll() {
        List<Product> products = repository.findAll();
        if (repository.findAll().isEmpty()){
            throw new ResourceNotFoundException("Products");
        }
        return repository.findAll().stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
    //Find by id
    public ProductResponse findById(Long id){
        return ProductResponse.fromEntity(repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Product")));
    }

    //FindByParams
    public List<ProductResponse> searchProducts(
                          String minPrice,
                          String maxPrice,
                          String name,
                          String sortBy,
                          String direction) {
        if (name != null) {
            name = name.toLowerCase();
        }
        Double minPriceFormated = null;
        Double maxPriceFormated = null;
        if (minPrice != null) {
            try {
                minPriceFormated = Double.parseDouble(minPrice);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("The parameter minPrice needs to be a number");
            }
        }
        if (maxPrice != null) {
            try {
                maxPriceFormated = Double.parseDouble(maxPrice);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("The parameter maxPrice needs to be a number");
            }
        }

        Sort sort = createSort(sortBy, direction);
        return repository.searchProducts(name, minPriceFormated, maxPriceFormated, sort).stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());

    }


    private Sort createSort(String sortBy, String direction) {
        if (sortBy == null || sortBy.isEmpty()){
            sortBy = "name";}
        else{
            if(!sortBy.equalsIgnoreCase("price") && !sortBy.equalsIgnoreCase("name")){
                throw new InvalidParamException("sortBy","name or price",sortBy);
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
