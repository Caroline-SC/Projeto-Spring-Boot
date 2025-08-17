package com.projetctJava.ProjectSpring.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
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



}
