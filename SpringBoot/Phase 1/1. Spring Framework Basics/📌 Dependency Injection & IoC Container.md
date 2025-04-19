## 🔹 1. What is Inversion of Control (IoC)?

**Inversion of Control** is a principle in software engineering where the control of object creation and dependency resolution is **inverted** from the application code to a **framework or container** (like Spring).

### 💡 Traditional Way vs IoC

**Without IoC:**

`UserService service = new UserService(new UserRepository());`

> You're manually creating and injecting dependencies.

**With IoC (Spring):**

`@Autowired`
`private UserService userService;`

> Spring manages and injects dependencies for you.

---

## 🔹 2. What is Dependency Injection (DI)?

**Dependency Injection** is a design pattern where an object's dependencies are provided (injected) by an external entity, rather than the object creating them itself.

### Types of DI in Spring:

|Type|Description|Annotation|
|---|---|---|
|Constructor Injection|Dependencies are provided via class constructor|`@Autowired` on constructor|
|Setter Injection|Dependencies are injected via setter methods|`@Autowired` on setter|
|Field Injection|Dependencies are injected directly into fields|`@Autowired` on field|

---

## 🔹 3. Spring IoC Container

The **IoC container** is the core of the Spring Framework. It manages the lifecycle, configuration, and dependencies of objects (called **beans**).

### 📦 Core Interfaces:

- **ApplicationContext** – Advanced, used 90% of the time
    
- **BeanFactory** – Basic container (used internally)
    

### Common Implementations:

- `AnnotationConfigApplicationContext` (Java config)
    
- `ClassPathXmlApplicationContext` (XML config)
    
- `WebApplicationContext` (for web apps)
    

---

## 🔹 4. What is a Bean?

A **Bean** is any object that is managed by the Spring IoC container.

You can define a bean using:

### ✅ Java Config:

```
@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}

```

### ✅ Component Scanning (Recommended):

```
@Component
public class UserService {
    //...
}

@Repository
public class UserRepository {
    //...
}
```

> Spring will scan these and register them as beans.

---

## 🔹 5. Important Annotations

|Annotation|Description|
|---|---|
|`@Component`|Generic bean for auto-detection|
|`@Service`|Marks a service class (business logic layer)|
|`@Repository`|Marks a DAO class (data access layer)|
|`@Controller`|Marks a web controller class|
|`@Autowired`|Automatically injects dependencies|
|`@Qualifier`|Specifies which bean to inject if multiple candidates exist|
|`@Primary`|Gives higher priority to a bean during autowiring|

---

## 🔹 6. Bean Scopes

By default, Spring beans are **singleton** (one instance per Spring container).

### Available Scopes:

|Scope|Description|
|---|---|
|`singleton`|One instance per container (default)|
|`prototype`|New instance for every request|
|`request`|One per HTTP request (web apps)|
|`session`|One per HTTP session (web apps)|

```
@Component
@Scope("prototype")
public class MyBean { }
```

---

## 🔹 7. Lifecycle of a Spring Bean

1. Instantiate
    
2. Populate properties (DI)
    
3. Call `@PostConstruct` or `init-method`
    
4. Use the bean
    
5. Call `@PreDestroy` or `destroy-method` (for singleton beans only)
    

---

## 🔹 8. How to Configure Spring

### Java Config (Recommended)

```
@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {
}
```

### XML Config (Legacy)

`<bean id="userService" class="com.example.UserService"/>`

---

## 🧠 Quick Concept Map:

                            ┌────────────────────┐
                            │    Application     │
                            │       Code         │
                            └────────▲───────────┘
                                     │
                                     │ Control Inversion
                                     ▼
                           ┌──────────────────────┐
                           │ Spring IoC Container │
                           └──────────────────────┘
                                      │
        ┌─────────────────────────────┼────────────────────────────┐
        ▼                             ▼                            ▼
┌──────────────────────┐    ┌──────────────────────┐    ┌──────────────────────┐
│  Spring IoC Container│    │ Dependency Injection │    │  Bean Lifecycle Mgmt │
└──────────────────────┘    └──────────────────────┘    └──────────────────────┘

---

## ✅ Common Interview Questions (for Phase 1)

1. What is Inversion of Control (IoC)?
    
2. What is Dependency Injection? How is it different from IoC?
    
3. What are the types of DI in Spring?
    
4. How do you define beans in Spring?
    
5. Difference between `@Component`, `@Service`, and `@Repository`?
    
6. What are bean scopes?
    
7. What is the difference between `ApplicationContext` and `BeanFactory`?
    
8. What happens during the Spring bean lifecycle?