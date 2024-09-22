package com.usermanagement.entities;

import com.usermanagement.constants.EntityConstants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testFirstNameNotNull() {
        User user = new User(1, null, "Doe", "password123", "john.doe@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(EntityConstants.EMPTY_FIRST_NAME)));
    }

    @Test
    public void testLastNameNotNull() {
        User user = new User(1, "John", null, "password123", "john.doe@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(EntityConstants.EMPTY_LAST_NAME)));
    }

    @Test
    public void testPasswordNotNull() {
        User user = new User(1, "John", "Doe", null, "john.doe@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(EntityConstants.EMPTY_PASSWORD)));
    }

    @Test
    public void testEmailNotNull() {
        User user = new User(1, "John", "Doe", "password123", null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(EntityConstants.EMPTY_EMAIL)));
    }

    @Test
    public void testEmailValid() {
        User user = new User(1, "John", "Doe", "password123", "john.doe@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

}
