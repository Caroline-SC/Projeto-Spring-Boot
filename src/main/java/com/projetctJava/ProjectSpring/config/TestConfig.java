package com.projetctJava.ProjectSpring.config;

import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import com.projetctJava.ProjectSpring.repositories.OrderRepository;
import com.projetctJava.ProjectSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
    Order order1 = new Order(null, Instant.parse("2024-08-19T15:54:06Z"), OrderStatus.DELIVERED);
    Order order2 = new Order(null, Instant.parse("2024-08-17T05:49:42Z"), OrderStatus.PAID);
    Order order3 = new Order(null, Instant.parse("2024-08-20T22:30:31Z"), OrderStatus.WAITING_PAYMENT);

    User user1 = new User(null,"Amanda Andrade Freitas","ammand.22@gmail.com","Rua Coqueirinho 25","99999-4321");
    User user2 = new User(null,"Maurilio Caetano Oliveira","maoli.8654@gmail.com","Avenida Lorenzo 310","99999-8765");


    orderRepository.saveAll(Arrays.asList(order1,order2,order3));
    userRepository.saveAll(Arrays.asList(user1,user2));

    }
}
