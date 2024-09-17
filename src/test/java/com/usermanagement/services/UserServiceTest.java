package com.usermanagement.services;

import java.util.*;

import static com.usermanagement.constants.ErrorConstants.EMAIL_ALREADY_EXISTS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.usermanagement.constants.ErrorConstants;
import com.usermanagement.entities.User;
import com.usermanagement.exceptions.DuplicateEmailException;
import com.usermanagement.exceptions.ResourceNotFoundException;
import com.usermanagement.repositories.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepositoryImpl userRepository;

    private User user;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        user = new User(1, "test", "user", "password123", "test@example.com");

        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    void findAll_ReturnsListOfUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    void findByID_ExistingId_ReturnsUser() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User result = userService.findByID(1);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void findByID_NonExistingId_ThrowsResourceNotFoundException() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.findByID(1));
        assertEquals(String.format(ErrorConstants.USER_NOT_FOUND, 1), thrown.getMessage());
    }

    @Test
    void save_NewUser_SuccessfullySavesUser() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.save(user);
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    @Transactional
    void save_ExistingEmail_ThrowsDuplicateEmailException() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
        DuplicateEmailException thrown = assertThrows(DuplicateEmailException.class, () -> userService.save(user));
        assertEquals(EMAIL_ALREADY_EXISTS, thrown.getMessage());
    }

    @Test
    @Transactional
    void deleteById_ExistingId_SuccessfullyDeletesUser() {
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);
        userService.deleteById(1);
        verify(userRepository).deleteById(id);
    }

    @Test
    @Transactional
    void deleteById_NonExistingId_ThrowsResourceNotFoundException() {
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(false);
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.deleteById(1));
        assertEquals(String.format(ErrorConstants.USER_NOT_FOUND, 1), thrown.getMessage());
    }
}

