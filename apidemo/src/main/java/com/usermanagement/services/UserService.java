package com.usermanagement.services;

import com.usermanagement.entities.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User findByID(int id);

    User save(User user);

    void deleteById(int id);

}
