## 📚 What is **Lombok**?

Lombok is a **Java annotation library** that generates commonly used code at **compile time** using **annotations**. This saves you from writing repetitive code in classes like:

- Getters / Setters
    
- Constructors
    
- `toString()`, `equals()`, `hashCode()`
    
- Builders
    
- Loggers
    

---

## 🛠️ How to Use Lombok in Spring Boot

---

### ✅ Step 1: Add Lombok Dependency

#### 🔹 If you're using **Maven** (`pom.xml`):

xml

CopyEdit

`<dependency>     <groupId>org.projectlombok</groupId>     <artifactId>lombok</artifactId>     <version>1.18.30</version>     <scope>provided</scope> </dependency>`

#### 🔹 If you're using **Gradle** (`build.gradle`):

groovy

CopyEdit

`dependencies {     compileOnly 'org.projectlombok:lombok:1.18.30'     annotationProcessor 'org.projectlombok:lombok:1.18.30' }`

### ✅ Step 2: Enable Annotation Processing in IDE

- **IntelliJ IDEA**: Preferences → Build, Execution, Deployment → Compiler → Annotation Processors → ✅ Enable
    
- **Eclipse**: Lombok jar must be installed using the installer
    

---

# 🧪 Example: Before vs After Using Lombok

---

### 🔴 Without Lombok (Old Way)

```
public class User {
    private Long id;
    private String name;
    private String email;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

---

### ✅ With Lombok (Clean & Modern)

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
}
```

> 🎉 Now you get getters, setters, constructor, `toString()`, `equals()`, and `hashCode()` automatically.

---

## 🧩 Common Lombok Annotations

|Annotation|What it does|
|---|---|
|`@Getter`, `@Setter`|Generates getters/setters for all fields or specific ones|
|`@ToString`|Generates a `toString()` method|
|`@EqualsAndHashCode`|Generates `equals()` and `hashCode()`|
|`@NoArgsConstructor`|Creates a no-argument constructor|
|`@AllArgsConstructor`|Creates a constructor with all fields|
|`@RequiredArgsConstructor`|Constructor for final fields / fields with `@NonNull`|
|`@Data`|Combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor`|
|`@Builder`|Implements builder pattern for clean object creation|
|`@Slf4j`|Adds a logger instance `log` (uses SLF4J)|
|`@Value`|Immutable version of `@Data` (all fields `private final`)|

---

### 🏗️ Example: Using `@Builder` and `@Slf4j`

```
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class Book {
    private String title;
    private String author;
    private double price;

    public void printInfo() {
        log.info("Book: {} by {} costs ${}", title, author, price);
    }
}
```

#### ✅ Usage:

```
Book book = Book.builder()
                .title("Spring Boot Mastery")
                .author("John Doe")
                .price(29.99)
                .build();

book.printInfo();
```

---

## 💡 When to Use Lombok

✅ Use it for:

- **DTOs**
    
- **Entity classes**
    
- **Model objects**
    
- **Logging (`@Slf4j`)**
    
- **Builder pattern**
    

🚫 Avoid in:

- Public APIs/shared libraries (some people prefer no "magic")
    
- Cases where compile-time visibility is critical for team
    

---
## 🧾 Lombok Cheat Sheet – Most Common Annotations

---

## 💼 Class-Level Annotations

|Annotation|Description|
|---|---|
|`@Getter`|Generates getters for all fields|
|`@Setter`|Generates setters for all fields|
|`@ToString`|Generates a `toString()` method|
|`@EqualsAndHashCode`|Generates `equals()` and `hashCode()` methods|
|`@NoArgsConstructor`|Generates a no-argument constructor|
|`@AllArgsConstructor`|Generates a constructor with all fields|
|`@RequiredArgsConstructor`|Constructor for `final` and `@NonNull` fields|
|`@Data`|Combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, `@RequiredArgsConstructor`|
|`@Value`|Like `@Data` but makes the class immutable (all fields `private final`)|
|`@Builder`|Implements Builder pattern|
|`@Slf4j`|Adds a `log` field using SLF4J logging framework|

---

## 🧪 Examples

---

### ✅ `@Data`

```
@Data
public class User {
    private Long id;
    private String name;
}
```

> ✨ Generates:
> 
> - Getters & Setters
>     
> - `toString()`
>     
> - `equals()` and `hashCode()`
>     
> - Constructor for required fields
>     

---

### ✅ `@Builder`

```
@Builder
public class Book {
    private String title;
    private String author;
    private double price;
}
```

> 🧱 Usage:

```
Book book = Book.builder()
                .title("Clean Code")
                .author("Uncle Bob")
                .price(39.99)
                .build();
```

---

### ✅ `@Slf4j`

```
@Slf4j
public class MyService {
    public void doSomething() {
        log.info("This is a log message");
    }
}
```

---

### ✅ `@NoArgsConstructor`, `@AllArgsConstructor`

```
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private int quantity;
}
```

---

### ✅ `@Value` (Immutable Class)

```
@Value
public class Address {
    String city;
    String zipCode;
}
```

---

## 🧠 Tips & Gotchas

- Annotations are processed at **compile-time**
    
- Use `@Builder(toBuilder = true)` to allow creating modified copies of an object
    
- `@Data` works best for DTOs and Entities
    
- `@Slf4j` avoids manual logger creation (`private static final Logger log = LoggerFactory.getLogger(...)`)
    
- IntelliJ must have **annotation processing enabled**
## 🧠 Interview Tip

> **Q: What does `@Data` do in Lombok?**  
> A: It is a shortcut for `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor`.