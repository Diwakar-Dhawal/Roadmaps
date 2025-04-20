## ✅ What is `@SpringBootApplication`?

### 🔹 It's a **meta-annotation**:

That means it's a combination of three core Spring annotations that are commonly used together.

### 📦 It expands to:

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public @interface SpringBootApplication {
    ...
}
```

---

## 🔍 Breakdown of the 3 Core Annotations:

---

### 1. `@SpringBootConfiguration`

- It's a specialization of `@Configuration`.
    
- Marks the class as a **source of bean definitions** for the application context.
    

```
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

When you use `@SpringBootApplication`, you can define beans right in that class if needed.

---

### 2. `@EnableAutoConfiguration`

- Tells Spring Boot to **auto-configure** your application based on the **dependencies** in the classpath.
    

✅ Example:  
If you add `spring-boot-starter-web`, it automatically sets up:

- Embedded Tomcat
    
- Spring MVC
    
- JSON converters
    

✅ Example:

```
@EnableAutoConfiguration
public class MyAutoConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(MyAutoConfigApp.class, args);
    }
}
```

---

### 3. `@ComponentScan`

- Automatically scans the package of the annotated class and its sub-packages to find:
    
    - `@Component`
        
    - `@Service`
        
    - `@Repository`
        
    - `@Controller`
        

You don’t need to manually register beans.

✅ Example:

```
@ComponentScan("com.example")
public class MyScannerConfig {}
```

Using `@SpringBootApplication` at the top of the root class **automatically triggers scanning** from that package downward.

---

## 🔧 Example: Real-World Usage

### 📁 Project Structure:

```
com.example.demo
├── DemoApplication.java
├── controller/
│   └── HelloController.java
└── service/
    └── GreetingService.java

```

---

### `DemoApplication.java`

```
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Combines @Configuration + @ComponentScan + @EnableAutoConfiguration
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

---

### `GreetingService.java`

```
package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
```

---

### `HelloController.java`

```
package com.example.demo.controller;

import com.example.demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "Spring") String name) {
        return greetingService.greet(name);
    }
}

```

---

### ▶️ Run:

`mvn spring-boot:run`

Then open:

`http://localhost:8080/greet?name=John`

You’ll get:

`Hello, John!`

---

## 🔁 Customizing `@SpringBootApplication`

You can also provide parameters to control behavior:

### ✅ Example: Scan a different package

`@SpringBootApplication(scanBasePackages = "com.custom.package")`

### ✅ Example: Exclude auto-config classes

`@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})`

---

## 🧠 Summary

|Feature|Purpose|
|---|---|
|`@SpringBootConfiguration`|Declares configuration and beans|
|`@EnableAutoConfiguration`|Enables Spring Boot's smart auto config|
|`@ComponentScan`|Automatically finds and registers beans|

🪄 `@SpringBootApplication` saves you from writing all three annotations individually — a perfect shortcut for clean, minimal setup.