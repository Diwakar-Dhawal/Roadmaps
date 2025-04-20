## ✅ `@Component`

### 📌 Purpose:

Marks a **Java class as a Spring-managed component (bean)**. It's the most **generic stereotype annotation**, and others like `@Service`, `@Repository`, and `@Controller` are specializations of it.

### 🧠 What it does:

- Tells Spring to detect and register this class as a **bean** during **component scanning**.
    
- The bean ID is the **class name with the first letter in lowercase** by default.
    

### 🔧 Example:



```
@Component 
public class EmailService {     

	public void sendEmail(String to) {         
		System.out.println("Sending email to " + to);     
	} 
	
}
```

### 🗃️ Configuration:

Enable component scanning:

```
@Configuration 
@ComponentScan("com.example") 
public class AppConfig { }
```

---

## ✅ `@Service`

### 📌 Purpose:

Used to mark a **service class** that holds **business logic**. It is **semantically more expressive** than `@Component`, but functionally the same.

> Internally, `@Service` is just a specialization of `@Component`.

### 🔧 Example:

```
@Service 
public class UserService {

	public String getUserDetails(Long id) {         
		return "User with id: " + id;     
	} 
	
}
```

### ✅ When to use:

Use `@Service` for **service layer** classes to improve code **readability and structure**.

---

## ✅ `@Repository`

### 📌 Purpose:

Used for **DAO (Data Access Object)** classes interacting with the **database**.

> Also a specialization of `@Component`, but with one big difference...

### 🧠 Extra Feature:

It provides **automatic exception translation** — converts database exceptions into **Spring's `DataAccessException`**.

### 🔧 Example:

```
@Repository 
public class UserRepository {    

	public void save(User user) {         // simulate DB save
	    System.out.println("Saving user: " + user.getName());     
  	} 
  	
}
```

### ✅ When to use:

Mark DAO classes with `@Repository` to benefit from **exception handling abstraction**.

---

## ✅ `@Autowired`

### 📌 Purpose:

Injects **bean dependencies** automatically from the Spring IoC container.

> Can be used on **constructor**, **field**, or **setter method**.

---

### 🔧 Field Injection (Not recommended for testing):


```
@Component 
public class UserController {      
	@Autowired     
	private UserService userService; 
}
```

---

### 🔧 Constructor Injection (Recommended for immutability and testability):

```
@Component 
public class UserController {     

	private final UserService userService;  
	    
	@Autowired     
	public UserController(UserService userService) {         
		this.userService = userService;     
	} 
}
```

---

### 🔧 Setter Injection:

```
@Component 
public class UserController {      
	private UserService userService;      
	
	@Autowired     
	public void setUserService(UserService userService) {
	    this.userService = userService;     
	} 
}
```

---

## ⚠️ Common Pitfalls and Tips

|Issue|Solution|
|---|---|
|Multiple beans of same type|Use `@Qualifier("beanName")`|
|Field injection hard to test|Prefer **constructor injection**|
|Circular dependencies|Break into separate beans or restructure logic|
|Need custom bean name|Use `@Component("customName")`|

---

## 🧠 Real-World Example


```
@Component
public class NotificationService {
    public void notify(String message) {
        System.out.println("Notification sent: " + message);
    }
}

@Service
public class OrderService {
    
    private final NotificationService notificationService;

    @Autowired
    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(String product) {
        System.out.println("Order placed for: " + product);
        notificationService.notify("Order placed successfully");
    }
}

@Repository
public class OrderRepository {
    public void save(String order) {
        System.out.println("Order saved to DB: " + order);
    }
}

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public String placeOrder(@RequestParam String product) {
        orderService.placeOrder(product);
        return "Order placed";
    }
}

```

---

## ✅ Summary Table

|Annotation|Role|Layer|Special Feature|
|---|---|---|---|
|`@Component`|Generic bean|All layers|Basic component registration|
|`@Service`|Business logic|Service layer|Semantic clarity|
|`@Repository`|Data access|DAO layer|Exception translation|
|`@Autowired`|Dependency injection|Across layers|Auto-wiring beans|