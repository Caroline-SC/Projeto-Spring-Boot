package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();
    Optional<User> findById(Long id);
}
