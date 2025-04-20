package com.phase1.springboot_basics.service;

import com.phase1.springboot_basics.model.User;
import com.phase1.springboot_basics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }
}

