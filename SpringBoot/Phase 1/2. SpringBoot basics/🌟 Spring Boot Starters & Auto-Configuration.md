## ✅ 1. What are Spring Boot Starters?

### 🔹 Definition:

A **starter** is a **predefined set of dependencies** in Spring Boot that you can include in your project to get a specific functionality without needing to search for compatible versions of each library.

> Think of them as **"shortcut packages"** to quickly bootstrap common app components.

---

### 📦 Example: Common Starters

|Starter|Description|
|---|---|
|`spring-boot-starter-web`|For building web (REST) apps using Spring MVC|
|`spring-boot-starter-data-jpa`|For working with relational databases using Spring Data JPA|
|`spring-boot-starter-security`|Adds Spring Security|
|`spring-boot-starter-test`|Adds testing libraries like JUnit, Mockito|
|`spring-boot-starter-thymeleaf`|For building server-side rendered web apps|

---

### 🔧 Usage in `pom.xml` (Maven)

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

💡 This single dependency pulls in:

- Spring MVC
    
- Jackson (for JSON)
    
- Embedded Tomcat
    
- Logging (SLF4J, Logback)
    

---

### 🧠 Why Use Starters?

✅ Simplifies dependency management  
✅ Reduces boilerplate setup  
✅ Ensures compatibility  
✅ Helps in modular development

---

## ✅ 2. What is Spring Boot Auto-Configuration?

### 🔹 Definition:

**Auto-Configuration** is a feature of Spring Boot that **automatically configures** your Spring application based on the **dependencies present on the classpath**.

> You don’t have to manually define beans or configurations unless you want to override them.

---

### 🔧 How It Works:

- Spring Boot uses **`@EnableAutoConfiguration`** (included in `@SpringBootApplication`) to scan the classpath.
    
- Based on what it finds (like `spring-boot-starter-data-jpa`), it automatically configures beans.
    

### 📍 Example:

If `spring-boot-starter-data-jpa` is on the classpath:

- Spring Boot auto-configures:
    
    - DataSource
        
    - JPA EntityManager
        
    - TransactionManager
        
    - Hibernate
        

So you can just do this:

java

CopyEdit

```
@Repository 
public interface UserRepository extends JpaRepository<User, Long> {}
```

✅ No need to define `EntityManagerFactory` or `DataSource` manually!

---

### 🧩 Internals:

- Spring Boot uses `META-INF/spring.factories` to register auto-configuration classes.
    
- These classes are conditionally loaded using annotations like:
    
    - `@ConditionalOnClass`
        
    - `@ConditionalOnMissingBean`
        
    - `@ConditionalOnProperty`
        

---

## 🛠️ Practical Example: Web Application

### `pom.xml` (Maven)

```
<dependency>     
	<groupId>org.springframework.boot</groupId>     
	<artifactId>spring-boot-starter-web</artifactId> 
</dependency>
```

### Main Application

```
@SpringBootApplication // Includes @EnableAutoConfiguration 
public class MyApp {     
	public static void main(String[] args) {    
	    SpringApplication.run(MyApp.class, args);     
	} 
}
```

### Controller

```
@RestController 
public class HelloController {     
	@GetMapping("/")    
	public String hello() {         
		return "Hello, Spring Boot!";     
	} 
}
```

➡️ No need to configure Tomcat, DispatcherServlet, JSON converters, etc. — it’s all **auto-configured**!

---

## 🧪 Overriding Auto-Configuration

If you want to customize behavior:

```
@Bean 
public ObjectMapper objectMapper() {     
	ObjectMapper mapper = new ObjectMapper();
	mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);     
	return mapper; 
}
```

Spring Boot will back off from auto-configuring `ObjectMapper` if you define your own — thanks to `@ConditionalOnMissingBean`.

---

## 🎯 Summary Table

|Feature|Spring Boot Starters|Auto-Configuration|
|---|---|---|
|Purpose|Bundled dependencies for a specific use case|Automatically configures Spring beans|
|Defined in|`pom.xml` / `build.gradle`|Auto-config classes in `spring.factories`|
|Developer effort|Just add dependency|Minimal; override only when needed|
|Example|`spring-boot-starter-web`|Auto-configures Spring MVC setup|
|Customization|Not directly customizable|Fully customizable via `@Bean`|

---

## ✅ Bonus Tips

- You can exclude a starter’s transitive dependency:
    

```
<exclusions>     
	<exclusion>        
		<groupId>org.springframework.boot</groupId>         
		<artifactId>spring-boot-starter-logging</artifactId>     
	</exclusion> 
</exclusions>
```

- See all auto-configured beans:
    
`http://localhost:8080/actuator/beans`

(Enable Actuator first)

```
+------------------------------+
|    You run the main class    |
+--------------+---------------+
               |
               v
+-------------------------------+
|  @SpringBootApplication       |
| (includes @EnableAutoConfig) |
+---------------+---------------+
                |
                v
+-------------------------------+
|  Classpath scanning begins    |
+-------------------------------+
                |
                v
+--------------------------------------------+
| Spring checks spring.factories for         |
| auto-config classes (like Web, JPA, etc.)  |
+-------------------------------+------------+
                                |
                                v
+---------------------------------------------------+
| Conditional checks (@ConditionalOnX)             |
| - Is a class present?                             |
| - Is a property set in application.properties?    |
| - Is a bean already defined?                      |
+-------------------------------+-------------------+
                                |
                                v
+---------------------------------------------+
|  Relevant Auto-Config classes are triggered |
| - WebMvcAutoConfiguration                   |
| - JpaRepositoriesAutoConfiguration          |
| - etc.                                      |
+-------------------------------+-------------+
                                |
                                v
+---------------------------------------------+
|  Beans are created and registered           |
| - DispatcherServlet                         |
| - DataSource                                |
| - Jackson ObjectMapper                      |
+---------------------------------------------+
```

## 🗂️ Sample Spring Boot Project Structure

```
spring-boot-demo/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/demo/
│       │       ├── DemoApplication.java      // Main class
│       │       ├── controller/
│       │       │   └── HelloController.java  // REST Controller
│       │       ├── service/
│       │       │   └── GreetingService.java  // Business logic
│       │       └── model/
│       │           └── User.java             // Entity (if using JPA)
│       └── resources/
│           ├── application.properties        // Configuration
│           └── static/                       // Static files (HTML, CSS)
├── pom.xml                                    // Maven dependencies
└── README.md
```
## 🧪 Project Practice Task

Here’s a mini task to get hands-on:

### 🧰 Setup:

In `pom.xml`:

```
<dependency>     
	<groupId>org.springframework.boot</groupId>     
	<artifactId>spring-boot-starter-web</artifactId> 
</dependency>
```

### 🎯 Code:

#### `GreetingService.java`

```
@Service 
public class GreetingService {     
	public String greet(String name) {         
		return "Hello, " + name + "!";     
	}
}
```

#### `HelloController.java`

```
@RestController 
public class HelloController {     

	@Autowired     
	private GreetingService greetingService; 
	     
	@GetMapping("/greet")     
	public String greet(@RequestParam(defaultValue = "World") String name) {
	    return greetingService.greet(name);     
	} 
}
```

#### `DemoApplication.java`

```
@SpringBootApplication 
public class DemoApplication {    

	public static void main(String[] args) {   
	    SpringApplication.run(DemoApplication.class, args);     
	}
	
}
```

### ▶️ Run the app and open:

`http://localhost:8080/greet?name=Spring`
