package com.usermanagement.services;

import com.usermanagement.exceptions.DuplicateEmailException;
import com.usermanagement.repositories.UserRepository;
import com.usermanagement.entities.User;
import com.usermanagement.constants.ErrorConstants;
import com.usermanagement.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.usermanagement.constants.ErrorConstants.EMAIL_ALREADY_EXISTS;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        logger.debug("Get all users.");
        List<User> users = userRepository.findAll();
        logger.debug("Received {} users", users.size());
        return users;
    }

    @Override
    public User findByID(int id) {
        logger.debug("Finding user with ID: {}", id);
        Long idAsLong = (long) id;
        return userRepository.findById(idAsLong).orElseThrow(() -> {
            logger.error("User with ID {} not found", id);
            return new ResourceNotFoundException(String.format(ErrorConstants.USER_NOT_FOUND, id));
        });
    }

    @Transactional
    @Override
    public User save(User user) {
        logger.info("Saving user: {}", user);
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.error("Attempted to save user with existing email: {}", user.getEmail());
            throw new DuplicateEmailException(EMAIL_ALREADY_EXISTS);
        }
        logger.info("Successfully saved user: {}", user);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        logger.info("Deleting user with ID: {}", id);
        Long idAsLong = (long) id;
        if (!userRepository.existsById(idAsLong)) {
            logger.error("User with ID {} not found for deletion", id);
            throw new ResourceNotFoundException(String.format(ErrorConstants.USER_NOT_FOUND, id));
        }
        userRepository.deleteById(idAsLong);
        logger.info("Successfully deleted user with ID: {}", id);
    }


}
