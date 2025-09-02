package com.projetctJava.ProjectSpring.services;

import com.projetctJava.ProjectSpring.dto.request.ProductCreateRequest;
import com.projetctJava.ProjectSpring.dto.request.ProductUpdateRequest;
import com.projetctJava.ProjectSpring.dto.response.OrderResponse;
import com.projetctJava.ProjectSpring.dto.response.ProductResponse;
import com.projetctJava.ProjectSpring.exceptions.custom.InvalidParamException;
import com.projetctJava.ProjectSpring.exceptions.custom.ResourceNotFoundException;
import com.projetctJava.ProjectSpring.models.Product;
import com.projetctJava.ProjectSpring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest){
        Product product = new Product(null, productCreateRequest.getName(),
                productCreateRequest.getDescription(), productCreateRequest.getPrice());
        repository.save(product);
       return ProductResponse.fromEntity(product);
    }
    @Transactional
    public ProductResponse updateProduct( Long id,ProductUpdateRequest productUpdateRequest){
        Product product = repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Product"));
        if (productUpdateRequest.getName() != null && !productUpdateRequest.getName().isEmpty()){
            product.setName(productUpdateRequest.getName());
        }
        if (productUpdateRequest.getDescription() != null && !productUpdateRequest.getDescription().isEmpty()){
            product.setDescription(productUpdateRequest.getDescription());
        }
        if (productUpdateRequest.getPrice() != null){
            product.setPrice(productUpdateRequest.getPrice());
        }
        Product updatedProduct = repository.save(product);
        return ProductResponse.fromEntity(updatedProduct);
    }
    @Transactional
    public void deleteById(Long id){
        Product product = repository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException(id, "Product"));
        repository.deleteById(id);
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
