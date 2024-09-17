package com.usermanagement.rest;

import com.usermanagement.exceptions.UserNotFoundException;
import com.usermanagement.services.UserService;
import com.usermanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class UserRestController {

    public static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        logger.debug("Request to find all users.");
        List<User> users = userService.findAll();
        logger.info("Found {} users", users.size());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getUser(@PathVariable int userID) {
        logger.debug("Request to find user with ID: {}", userID);
        User user = userService.findByID(userID);
        if (user == null) {
            logger.warn("User ID not found: {}", userID);
            return ResponseEntity.notFound().build();
            //throw new RuntimeException("ID not found - " + userID);
        }
        logger.info("User found with ID: {}", userID);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        logger.debug("Request to add user: {}", user);
        User createdUser = userService.save(user);
        logger.info("User added with ID: {}", createdUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        logger.debug("Request to update user: {}", user);
        User updatedUser = userService.save(user);
        logger.info("User updated with ID: {}", updatedUser.getId());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable int userID) throws UserNotFoundException {
        logger.debug("Request to delete user with ID: {}", userID);
        User tempUser = userService.findByID(userID);
        if (tempUser == null) {
            logger.warn("User ID not found: {}", userID);
            return ResponseEntity.notFound().build();
            //throw new UserNotFoundException(userID);
        }
        userService.deleteById(userID);
        logger.info("User deleted with ID: {}", userID);
        return ResponseEntity.ok("Deleted user id - " + userID);
        //return "Deleted user id - " + userID;
    }

}

