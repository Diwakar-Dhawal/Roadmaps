package com.phase1.springboot_basics;


import com.phase1.springboot_basics.model.User;
import com.phase1.springboot_basics.repository.UserRepository;
import com.phase1.springboot_basics.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = List.of(new User(1L, "Test", "test@example.com"));
        when(userRepository.findAllUsers()).thenReturn(mockUsers);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
        verify(userRepository).findAllUsers();
    }
}

