package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    Optional<Order> findById(Long id);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByMomentBefore(Instant day);
    List<Order> findByMomentAfter(Instant day);
    List<Order> findByMomentBetween(Instant startday, Instant endday);
    List<Order> findByClientId(Long id);
}
