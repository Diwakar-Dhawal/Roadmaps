## 🧠 What is `@RestController`?

### 🔹 Definition:

`@RestController` is a Spring annotation that is used to **create RESTful web services** using Spring MVC.

It's a **convenient shortcut** that combines:

`@Controller + @ResponseBody`

> This means **every method in a `@RestController` returns a response body directly**, usually in JSON or XML.

---

## ✅ Use Case:

Build web APIs that clients (like frontend apps or mobile apps) can call to **send or retrieve data** using HTTP methods: `GET`, `POST`, `PUT`, `DELETE`.

---

## 🛠️ Example Project Structure:

```
com.example.demo
├── DemoApplication.java
├── controller/
│   └── UserController.java
└── model/
    └── User.java
```

---

## 📦 Step-by-Step: Create a Simple REST API

---

### 1️⃣ Model Class – `User.java`

```
package com.example.demo.model;

public class User {
    private Long id;
    private String name;
    private String email;

    // Constructors
    public User() {}
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    // ...
}
```

---

### 2️⃣ REST Controller – `UserController.java`

```
package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Temporary in-memory store
    private Map<Long, User> userStore = new HashMap<>();
    private Long idCounter = 1L;

    // GET all users
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    }

    // GET user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userStore.get(id);
    }

    // POST create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(idCounter++);
        userStore.put(user.getId(), user);
        return user;
    }

    // PUT update existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userStore.put(id, user);
        return user;
    }

    // DELETE a user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userStore.remove(id);
        return "User deleted";
    }
}
```

---

## 🧪 How to Test These APIs

You can test using:

### 🔹 Postman / Insomnia

**Base URL**: `http://localhost:8080/api/users`

- `GET /` → Get all users
    
- `POST /` with JSON body:
    
```
{
  "name": "Alice",
  "email": "alice@example.com"
}
```

- `GET /1` → Get user with ID 1
    
- `PUT /1` → Update user
    
- `DELETE /1` → Delete user
    

---

## 💡 Explanation of Key Annotations

|Annotation|Purpose|
|---|---|
|`@RestController`|Combines `@Controller` + `@ResponseBody`, making all methods return JSON|
|`@RequestMapping`|Set base path (optional)|
|`@GetMapping`|Handles GET requests|
|`@PostMapping`|Handles POST (create)|
|`@PutMapping`|Handles PUT (update)|
|`@DeleteMapping`|Handles DELETE|
|`@PathVariable`|Captures path parameters from URL|
|`@RequestBody`|Maps request JSON to Java object|

---

## 🎯 Best Practices

1. ✅ Separate **Controller**, **Service**, and **Repository** layers.
    
2. ✅ Validate request bodies using `@Valid` + JSR-303.
    
3. ✅ Return `ResponseEntity<T>` for better HTTP control.
    
4. ✅ Use status codes like `201 Created`, `404 Not Found`, etc.
    
5. ✅ Handle errors with `@ControllerAdvice` and exception handlers.
    
6. ✅ Use Swagger/OpenAPI for auto-documenting your APIs.
    

---

## 🚀 Bonus: Example with `ResponseEntity`

```
@GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userStore.get(id);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);
}
```

---

## 🔚 Summary

|Feature|Description|
|---|---|
|`@RestController`|Builds REST APIs returning JSON/XML|
|Easy to use|Just annotate and define methods with `@GetMapping`, etc.|
|JSON Mapping|Automatically maps Java objects to JSON using Jackson|
|Stateless|Perfect for modern RESTful services|
