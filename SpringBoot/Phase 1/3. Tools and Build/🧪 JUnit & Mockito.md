## 🧪 What is JUnit?

**JUnit** is the **standard testing framework** for unit testing in Java. It allows you to write test cases to verify if your logic behaves as expected.

### ✅ Basic JUnit Annotations

|Annotation|Description|
|---|---|
|`@Test`|Marks a method as a test|
|`@BeforeEach`|Runs before each test|
|`@AfterEach`|Runs after each test|
|`@BeforeAll`|Runs once before all tests (static)|
|`@AfterAll`|Runs once after all tests (static)|
|`@Disabled`|Skips the test|
|`@DisplayName`|Gives a readable name to a test|

---

## 🧪 What is Mockito?

**Mockito** is a mocking framework that allows you to create **fake objects (mocks)** to **simulate behavior** and **isolate the unit being tested**.

You use it to:

- **Mock dependencies**
    
- **Verify interactions**
    
- **Stub method returns**
    

---

# 🔧 Example: Testing a Service Layer

Let’s say you have a simple service that depends on a repository:

### 📁 `UserService.java`

```
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
```

### 📁 `UserRepository.java`

```
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

## ✅ Testing with JUnit & Mockito

### 📁 `UserServiceTest.java`

```
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        User user = new User(1L, "Alice", "alice@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("Alice", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(
            new User(1L, "Alice", "alice@example.com"),
            new User(2L, "Bob", "bob@example.com")
        );

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }
}
```

---

## 🧠 Key Concepts in Mockito

| Concept          | Syntax Example                            |
| ---------------- | ----------------------------------------- |
| Mock a class     | `@Mock UserRepository userRepository;`    |
| Inject mocks     | `@InjectMocks UserService userService;`   |
| Stub a method    | `when(repo.findById(1L)).thenReturn(...)` |
| Verify a call    | `verify(repo).findById(1L);`              |
| Argument matcher | `any(), eq(), anyLong()` etc.             |
| Return exception | `when(repo.save(any())).thenThrow(...)`   |

---

## 🔥 Bonus: Testing Controller with MockMvc

```
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserById() throws Exception {
        User user = new User(1L, "Alice", "alice@example.com");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Alice"));
    }
}
```

---

## 🧠 Interview Tip

> **Q: Why use Mockito instead of real objects in tests?**  
> A: Real objects might require databases, services, or complex setups. Mockito lets us test **only the logic** of the method being tested by simulating behavior of other classes.