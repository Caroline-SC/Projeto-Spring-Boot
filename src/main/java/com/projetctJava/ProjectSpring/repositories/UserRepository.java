package com.projetctJava.ProjectSpring.repositories;

import com.projetctJava.ProjectSpring.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR REPLACE(LOWER(u.name),' ','') LIKE CONCAT('%',:name,'%')) AND " +
            "(:email IS NULL OR u.email = :email) AND " +
            "(:number IS NULL OR u.phoneNumber = :number) AND " +
            "(:address IS NULL OR REPLACE(LOWER(u.address),' ','') LIKE CONCAT('%',:address,'%'))")
    List<User> searchUser(
            @Param("name")String name,
            @Param("email")String email,
            @Param("number")String number,
            @Param("address")String address,
            Sort sort);
}
