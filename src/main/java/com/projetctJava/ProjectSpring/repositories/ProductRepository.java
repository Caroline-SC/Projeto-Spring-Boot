package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor {

    List<Product> findAll();
    Optional<Product> findById(Long id);

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR REPLACE(LOWER(p.name),' ','') LIKE CONCAT('%',:name,'%')) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> searchProducts(
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Sort sort);

}
