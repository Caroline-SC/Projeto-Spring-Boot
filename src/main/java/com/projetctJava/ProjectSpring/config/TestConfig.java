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

    User user1 = new User(null,"Amanda Andrade Freitas","ammand.22@gmail.com","Rua Coqueirinho 25","99999-4321");
    User user2 = new User(null,"Maurilio Caetano Oliveira","maoli.8654@gmail.com","Avenida Lorenzo 310","99999-8765");
    User user3 = new User(null, "João Silva Santana", "joao.silva@example.com", "Rua das Flores 100", "99999-1234");
    User user4 = new User(null, "Maria Oliveira Santos", "maria.oliveira@example.com", "Avenida Principal 200", "99999-5678");


    Order order1 = new Order(null, Instant.parse("2024-08-19T15:54:06Z"), OrderStatus.DELIVERED, user1);
    Order order2 = new Order(null, Instant.parse("2024-08-17T05:49:42Z"), OrderStatus.PAID, user1);
    Order order3 = new Order(null, Instant.parse("2024-08-20T22:30:31Z"), OrderStatus.WAITING_PAYMENT,user2);

    userRepository.saveAll(Arrays.asList(user1,user2));
    orderRepository.saveAll(Arrays.asList(order1,order2,order3));

    user1.getOrders().addAll(Arrays.asList(order1,order2));
    user2.getOrders().addAll(Arrays.asList(order3));

    userRepository.saveAll(Arrays.asList(user1,user2,user3,user4));

    }
}
