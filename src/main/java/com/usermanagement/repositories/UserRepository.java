package com.usermanagement.repositories;

import com.usermanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findById(int id);

    User save(User user);

    void deleteById(int id);

    boolean existsByEmail(String email);

}
