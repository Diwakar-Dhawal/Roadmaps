## 🚀 What is Spring Boot DevTools?

**Spring Boot DevTools** is a module provided by Spring Boot that boosts your development experience by enabling:

✅ **Auto-restart on code changes**  
✅ **LiveReload in the browser**  
✅ **Caching disabled automatically**  
✅ **Remote debugging support**  
✅ **Fast feedback during development**

---
## 📦 How to Add DevTools

---

### 🔹 Maven

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

> 🔁 Use `runtime` so it’s excluded in production builds.

---

### 🔹 Gradle

```
dependencies {
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
}
```

---

## 🧠 What It Does Behind the Scenes

|Feature|Description|
|---|---|
|**Automatic Restart**|App restarts whenever a file on the classpath changes|
|**LiveReload**|Automatically refreshes browser when HTML/Thymeleaf/JS/CSS changes|
|**Disabled Caching**|Template engines like Thymeleaf don’t cache templates in dev mode|
|**Global Properties**|Enables dev-friendly settings automatically (`spring.thymeleaf.cache=false`)|
|**Remote Debugging**|Supports remote restart/debugging in IDEs|

---

## 🔁 How Auto-Restart Works

Spring Boot DevTools uses **two classloaders**:

1. **Base classloader** – Loads stable classes (dependencies)
    
2. **Restart classloader** – Reloads changed classes (your code)
    

Only your source code classes are reloaded – which makes restarts **faster than full application reboot**.

---

## 💡 Common `application.properties` Settings for DevTools

```
# Enable LiveReload server (default = true)
spring.devtools.livereload.enabled=true

# Disable restart (useful when debugging)
spring.devtools.restart.enabled=false

# Exclude paths from restart trigger
spring.devtools.restart.exclude=static/**,public/**

# Watch custom directories for changes
spring.devtools.restart.additional-paths=some/custom/path
```

---

## 🧪 Simple Example

### 🎯 Step 1: Add DevTools dependency in `pom.xml`

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 🎯 Step 2: Create a controller

```
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }
}
```

### 🎯 Step 3: Change the return value and **save**

```
	return "👋 Updated response after hot reload!";
```

> DevTools will automatically **restart the app** and your browser/API tool will see the new output **without manually stopping and restarting.**

---

## 🌍 LiveReload in Browser

1. Install LiveReload extension in Chrome
    
2. Start your app with DevTools
    
3. Enable the LiveReload extension
    
4. Change HTML templates (if using Thymeleaf) → Page refreshes automatically!
    

---

## 🚫 DevTools Is Dev-Only

- DevTools is **not included in production jars**
    
- It is **disabled automatically** in remote environments (e.g., production server)
    
> 💡 Spring Boot checks `spring.devtools.remote.secret` and other conditions to disable it for safety.

---

## 🧠 Interview Tip

> **Q: What’s the benefit of Spring Boot DevTools?**  
> A: It improves developer productivity by allowing auto-restarts, disabling template caching, and enabling LiveReload without affecting production.