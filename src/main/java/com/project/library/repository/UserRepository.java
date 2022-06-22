package com.project.library.repository;

import com.project.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE email = ?", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> findAllUsers();
}
