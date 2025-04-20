## 📁 Project Structure:

```
com.example.demo
├── DemoApplication.java
├── controller/
│   └── UserController.java
├── service/
│   └── UserService.java
├── exception/
│   ├── UserNotFoundException.java
│   └── GlobalExceptionHandler.java
├── model/
│   └── User.java
```

---

### 📌 1. `DemoApplication.java`

```
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
```

---

### 📌 2. `model/User.java`

```
package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
```

---

### 📌 3. `service/UserService.java`

```
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private Map<Long, User> userRepo = new HashMap<>();
    private Long idCounter = 1L;

    public List<User> getAllUsers() {
        return new ArrayList<>(userRepo.values());
    }

    public User getUserById(Long id) {
        User user = userRepo.get(id);
        if (user == null) throw new UserNotFoundException("User not found with ID: " + id);
        return user;
    }

    public User createUser(User user) {
        user.setId(idCounter++);
        userRepo.put(user.getId(), user);
        return user;
    }

    public User updateUser(Long id, User user) {
        if (!userRepo.containsKey(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        user.setId(id);
        userRepo.put(id, user);
        return user;
    }

    public void deleteUser(Long id) {
        if (!userRepo.containsKey(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepo.remove(id);
    }
}
```

---

### 📌 4. `controller/UserController.java`

```
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

```

---

### 📌 5. `exception/UserNotFoundException.java`

```
package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

---

### 📌 6. `exception/GlobalExceptionHandler.java`

```
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
```
---

## 🧪 Test This API

You can use [Postman](https://www.postman.com/) or [curl] to test:

- **GET** `http://localhost:8080/api/users`
    
- **POST** with body:
    
```
{
  "name": "John",
  "email": "john@example.com"
}
```

- **PUT**, **DELETE** work similarly