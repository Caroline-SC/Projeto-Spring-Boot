package com.projetctJava.ProjectSpring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String address;
    @Column(unique = true)
    private String phoneNumber;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public User(Long id, String name, String email, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
