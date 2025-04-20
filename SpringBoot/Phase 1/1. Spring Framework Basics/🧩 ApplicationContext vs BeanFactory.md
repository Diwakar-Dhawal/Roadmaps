## 🔹 1. What are They?

|Concept|Description|
|---|---|
|`BeanFactory`|The **basic IoC container** in Spring. Provides the foundational mechanism to manage beans.|
|`ApplicationContext`|A **more advanced container** built on top of `BeanFactory`. It provides everything `BeanFactory` does **plus many additional features** useful for enterprise applications.|

---

## 🔹 2. Interface Hierarchy


```
BeanFactory
   ↑
ApplicationContext
```

> `ApplicationContext` **extends** `BeanFactory`.

---

## 🔹 3. Key Differences

|Feature|BeanFactory|ApplicationContext|
|---|---|---|
|Instantiation|**Lazy** (beans created on demand)|**Eager** (beans created at startup)|
|Annotation support|Limited|Full (e.g., `@Component`, `@Autowired`)|
|Internationalization (i18n)|❌ No|✅ Yes|
|Event handling|❌ No|✅ Yes|
|AOP Integration|❌ Basic|✅ Full support|
|`@PostConstruct` support|❌ No|✅ Yes|
|Environment abstraction|❌ No|✅ Yes|
|Bean lifecycle callbacks|❌ Manual|✅ Automatic|
|Used in|Lightweight or legacy apps|Real-world, modern apps|

---

## 🔹 4. When to Use What?

|Use Case|Recommended Container|
|---|---|
|Lightweight CLI app or testing|`BeanFactory`|
|Enterprise-level, Spring Boot app|`ApplicationContext`|
|Need annotations like `@ComponentScan`, `@Value`, or `@Autowired`|`ApplicationContext`|
|Need event publishing, resource loading|`ApplicationContext`|

---

## 🔧 5. Code Example

### 🔸 Using **BeanFactory**

```
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryExample {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));

        MyService service = (MyService) factory.getBean("myService");
        service.doSomething();
    }
}
```

### XML Config (`beans.xml`)

`<beans>     <bean id="myService" class="com.example.MyService"/> </beans>`

---

### 🔸 Using **ApplicationContext**

```
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        MyService service = context.getBean(MyService.class);
        service.doSomething();
    }
}
```

✅ `ApplicationContext` supports:

- `@ComponentScan`
    
- Resource loading
    
- Event publishing
    
- Environment abstraction
    

---

## 🧪 Example with `@Component`

### `MyService.java`

```
@Component
public class MyService {
    public void doSomething() {
        System.out.println("Doing something important!");
    }
}
```

### AppConfig.java

```
@Configuration 
@ComponentScan(basePackages = "com.example")
public class AppConfig {}
```

### Main.java

```
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService service = context.getBean(MyService.class);
        service.doSomething();
    }
}

```

🛑 You **can’t do this** with `BeanFactory` — it won’t process annotations or lifecycle callbacks automatically.

---

## 🧠 Memory & Performance Tip

- `BeanFactory` is **lighter** and loads beans **lazily**, so it may be slightly more memory-efficient in certain edge cases.
    
- `ApplicationContext` eagerly loads beans on startup, ensuring you catch misconfigurations early.
    

---

## ✅ Summary

|Criteria|BeanFactory|ApplicationContext|
|---|---|---|
|Interface|org.springframework.beans.factory.BeanFactory|org.springframework.context.ApplicationContext|
|Bean Instantiation|Lazy|Eager|
|Annotation Support|❌|✅|
|Event Handling|❌|✅|
|AOP, i18n, Profiles|❌|✅|
|Use In|Very basic apps or unit tests|Most Spring applications|