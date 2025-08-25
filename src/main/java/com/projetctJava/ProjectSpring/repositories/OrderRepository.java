package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    Optional<Order> findById(Long id);
    List<Order> findByClientId(Long id);

    @Query("SELECT o FROM Order o " +
            "WHERE (:status IS NULL OR o.status = :status) " +
            "AND (:clientName IS NULL OR LOWER(o.client.name) LIKE LOWER(CONCAT('%', :clientName, '%'))) " +
            "AND (:startMoment IS NULL OR o.moment >= :startMoment) " +
            "AND (:endMoment IS NULL OR o.moment <= :endMoment) ")
    List<Order> searchOrder(
            @Param("status") OrderStatus status,
            @Param("clientName") String clientName,
            @Param("startMoment") Instant startMoment,
            @Param("endMoment") Instant endMoment,
            Sort sort
    );
}
