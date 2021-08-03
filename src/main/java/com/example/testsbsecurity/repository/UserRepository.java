package com.example.testsbsecurity.repository;


import com.example.testsbsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories()
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String s);
}
