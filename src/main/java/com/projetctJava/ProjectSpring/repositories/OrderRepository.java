package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
