package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.Product;
import com.projetctJava.ProjectSpring.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor {

    List<Product> findAll();
    Optional<Product> findById(Long id);

}
