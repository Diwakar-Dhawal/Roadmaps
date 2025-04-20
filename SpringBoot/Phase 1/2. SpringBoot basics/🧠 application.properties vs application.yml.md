## ✅ 1. Purpose of Both Files

Both files are used to **configure your Spring Boot application**:

- Server settings (port, context path)
    
- Database configurations
    
- Logging
    
- Profiles
    
- Third-party integrations
    
- Custom app properties
    

📌 You only need **one** of them — Spring Boot will prioritize based on availability and location.

---

## 📄 2. `application.properties`

### 🔹 Format: `Key = Value`

It’s a simple key-value pair format, similar to `.env` files.

---

### ✅ Example: `application.properties`

```
# Server settings
server.port=8081
server.servlet.context-path=/myapp

# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/demo
spring.datasource.username=root
spring.datasource.password=secret

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Custom property
app.greeting.message=Hello from properties
```

---

## 📘 3. `application.yml`

### 🔹 Format: YAML (YAML Ain't Markup Language)

YAML is more readable, especially for **nested configurations**. Uses indentation instead of dots and equals signs.

---

### ✅ Example: `application.yml`

```
server:
  port: 8081
  servlet:
    context-path: /myapp

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/demo
    username: root
    password: secret

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

app:
  greeting:
    message: Hello from YAML
```

---

## 🔄 4. Feature Comparison

|Feature|`application.properties`|`application.yml`|
|---|---|---|
|Format|Key-Value (flat)|Hierarchical, Indented|
|Readability|Simple but verbose|Clean & concise|
|Nesting support|Not intuitive|Natural & clean|
|Profiles|Easy|Slightly more complex|
|Read by Spring Boot?|✅ Yes|✅ Yes|

---

## 📂 5. Spring Boot Priority (Default)

Spring Boot reads properties in the following order (higher wins):

1. `application.properties` or `application.yml` in `/config` directory
    
2. `application.properties` or `application.yml` in root `src/main/resources`
    
3. Command-line arguments
    
4. Environment variables
    

So you can have:

`src/main/resources/config/application.yml`

or

`src/main/resources/application.properties`

---

## 🌐 6. Profile-specific Configuration

### 🔸 application.properties

```
# application-dev.properties
server.port=8082

# application-prod.properties
server.port=80
```
### 🔸 application.yml

```
spring:
  profiles:
    active: dev

---
spring:
  profiles: dev
server:
  port: 8082

---
spring:
  profiles: prod
server:
  port: 80

```

---

## 🎯 7. Access Custom Properties in Java

### application.properties

```
	app.greeting.message=Hello, Dev!
```

### application.yml

```
app:
  greeting:
    message: Hello, YAML!
```

### Java Code to Access:

```
@Component
@ConfigurationProperties(prefix = "app.greeting")
public class GreetingConfig {
    private String message;

    // getters and setters
}
```

---

## ✅ Summary

|Criteria|`application.properties`|`application.yml`|
|---|---|---|
|Simplicity|✅ Very simple|🔸 Clean but needs spacing awareness|
|Nesting|❌ Hard to manage|✅ Easy and readable|
|Profiles|✅ Easy to switch|✅ Better in some complex cases|
|YAML errors|❌ Not applicable|⚠️ Sensitive to indenting|
|Most used in|Older projects|Modern Spring Boot apps & microservices|

---

## 🤔 Which Should You Use?

- ✅ Use **`application.yml`** if:
    
    - You prefer hierarchical configs (especially in microservices)
        
    - Want more clarity and less redundancy
        
- ✅ Use **`application.properties`** if:
    
    - You like flat structure
        
    - Want simpler syntax for small apps