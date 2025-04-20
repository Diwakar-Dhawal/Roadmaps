package com.phase1.springboot_basics.repository;

import org.springframework.stereotype.Repository;
import com.phase1.springboot_basics.model.User;
import java.util.List;

@Repository
public class UserRepository {
    public List<User> findAllUsers() {
        return List.of(
                new User(1L, "Alice", "alice@example.com"),
                new User(2L, "Bob", "bob@example.com")
        );
    }
}

