package com.projetctJava.ProjectSpring.config;

import com.projetctJava.ProjectSpring.models.Order;
import com.projetctJava.ProjectSpring.models.OrderItem;
import com.projetctJava.ProjectSpring.models.Product;
import com.projetctJava.ProjectSpring.models.User;
import com.projetctJava.ProjectSpring.models.enums.OrderStatus;
import com.projetctJava.ProjectSpring.repositories.OrderRepository;
import com.projetctJava.ProjectSpring.repositories.ProductRepository;
import com.projetctJava.ProjectSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {

    User user1 = new User(null,"Amanda Andrade Freitas","ammand.22@gmail.com","Rua Coqueirinho 25","999994321");
    User user2 = new User(null,"Maurilio Caetano Oliveira","maoli.8654@gmail.com","Avenida Lorenzo 310","999998765");
    User user3 = new User(null, "João Silva Santana", "joao.silva@example.com", "Rua das Flores 100", "999991234");
    User user4 = new User(null, "Maria Oliveira Santos", "maria.oliveira@example.com", "Avenida Principal 200", "999995678");

    userRepository.saveAll(Arrays.asList(user1,user2));

    Order order1 = new Order(null, Instant.parse("2024-08-19T15:54:06Z"), OrderStatus.DELIVERED, user1);
    Order order2 = new Order(null, Instant.parse("2024-08-17T05:49:42Z"), OrderStatus.PAID, user1);
    Order order3 = new Order(null, Instant.parse("2024-08-20T22:30:31Z"), OrderStatus.WAITING_PAYMENT,user2);

    Product product1 = new Product(null,"Teclado gamer","Um teclado perfeito para jogar",200.90);
    Product product2 = new Product(null,"Bola de basquete","Bola de basquete profissional da Spadding",89.90);
    Product product3 = new Product(null,"Terno Preto DAB","Um terno social",200.90);
    Product product4 = new Product(null,"Memorias do Subsolo","Livro do Dostoiévski",40.90);
    Product product5 = new Product(null,"Pasta Termica","Pasta termica par acpu",20.90);

    productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));

    OrderItem orderItem1 = new OrderItem(2,product2);
    OrderItem orderItem2 = new OrderItem(1,product4);
    OrderItem orderItem3 = new OrderItem(5,product5);
    OrderItem orderItem4 = new OrderItem(1,product1);
    OrderItem orderItem5 = new OrderItem(3,product3);
    OrderItem orderItem6 = new OrderItem(5,product4);


    order1.addItem(orderItem1);
    order2.addItems(Arrays.asList(orderItem2,orderItem3));
    order3.addItems(Arrays.asList(orderItem4,orderItem5,orderItem6));

    orderRepository.saveAll(Arrays.asList(order1,order2,order3));

    user1.getOrders().addAll(Arrays.asList(order1,order2));
    user2.getOrders().addAll(Arrays.asList(order3));

    userRepository.saveAll(Arrays.asList(user1,user2,user3,user4));



    }
}
