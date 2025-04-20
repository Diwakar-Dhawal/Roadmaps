## 🔹 What are Maven and Gradle?

Both are **build automation tools** for Java applications.

|Feature|Maven|Gradle|
|---|---|---|
|Language|XML (`pom.xml`)|Groovy/Kotlin DSL (`build.gradle`)|
|Build speed|Slower|Faster (with incremental builds)|
|Readability|Verbose|Concise and scriptable|
|Learning curve|Easier for beginners|Flexible, slightly steeper|
|Used with Spring Boot|✅ Common|✅ Very popular in modern setups|

---

## ✅ Why We Use Build Tools?

- **Manage dependencies** (like Spring Boot Starter Web, MySQL connector)
    
- **Build & package the app** (`.jar`, `.war`)
    
- **Run tests**
    
- **Generate reports**
    
- **Integrate with CI/CD pipelines**
    

---

## 📦 Maven Basics

---

### 📁 Standard Maven Project Structure

```
project/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        ├── java/
        └── resources/
```

---

### 📄 `pom.xml` (Project Object Model)

Your entire project config (dependencies, plugins, build setup) is declared in this XML file.

#### ✅ Sample `pom.xml`

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>demo</name>
  <description>Spring Boot Demo with Maven</description>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.0</version>
    <relativePath/>
  </parent>

  <properties>
    <java.version>17</java.version>
  </properties>

  <dependencies>
    <!-- Spring Boot Web Starter -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
      <groupId>jakarta.validation</groupId>
      <artifactId>jakarta.validation-api</artifactId>
    </dependency>

    <!-- Testing -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>

</project>
```

---

### 🔧 Common Maven Commands

|Command|Description|
|---|---|
|`mvn clean`|Deletes the `target/` directory|
|`mvn install`|Builds the project and installs into `.m2`|
|`mvn package`|Compiles and packages the app (`.jar`)|
|`mvn spring-boot:run`|Runs your Spring Boot app|
|`mvn test`|Runs tests|

---

## ⚡ Gradle Basics

---

### 📁 Standard Gradle Project Structure

Same as Maven, but config is in `build.gradle`.

---

### 📄 Sample `build.gradle`

```
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.0'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'jakarta.validation:jakarta.validation-api'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

---

### ⚙ Common Gradle Commands

|Command|Description|
|---|---|
|`./gradlew clean`|Cleans the build output|
|`./gradlew build`|Compiles and builds `.jar`|
|`./gradlew bootRun`|Runs your Spring Boot app|
|`./gradlew test`|Runs all tests|
|`./gradlew dependencies`|Shows dependency tree|

---

## 🆚 Maven vs Gradle Summary

|Feature|Maven|Gradle|
|---|---|---|
|Config file|`pom.xml`|`build.gradle`|
|Syntax|XML|Groovy/Kotlin DSL|
|Build speed|Slower|Faster (incremental build)|
|Dependency Mgmt|Maven Central|Maven Central + others|
|Plugins|XML plugins|Rich plugin support|
|IDE Support|Excellent|Excellent|

---

## 🧠 Which Should You Use?

✅ **Maven** is great if:

- You’re new to Java or Spring
    
- You prefer structured and predictable builds
    

⚡ **Gradle** is great if:

- You want flexibility and faster builds
    
- You’re working in modern microservices

# 🧰 Maven Command Cheat Sheet (`pom.xml`)

|Command|What It Does|
|---|---|
|`mvn clean`|Deletes the `target/` folder (removes old build files)|
|`mvn compile`|Compiles the source code|
|`mvn test`|Runs unit tests|
|`mvn package`|Packages the project into a `.jar` or `.war` inside `target/`|
|`mvn install`|Builds and installs it into your local Maven repo (`.m2`)|
|`mvn spring-boot:run`|Runs your Spring Boot app directly without packaging|
|`mvn dependency:tree`|Shows your full dependency hierarchy|
|`mvn clean install -DskipTests`|Installs without running tests|
|`mvn help:effective-pom`|Shows the final merged `pom.xml` after inheritance/properties|

---

# ⚡ Gradle Command Cheat Sheet (`build.gradle`)

|Command|What It Does|
|---|---|
|`./gradlew clean`|Deletes the `build/` folder|
|`./gradlew build`|Compiles, tests, and packages the project into `.jar`|
|`./gradlew bootRun`|Runs the Spring Boot app|
|`./gradlew test`|Runs unit tests|
|`./gradlew dependencies`|Shows the full dependency tree|
|`./gradlew tasks`|Lists all available Gradle tasks|
|`./gradlew assemble`|Builds only `.class` files, no tests or `.jar`|
|`./gradlew build --scan`|Generates a build scan (great for debugging)|
|`./gradlew bootJar`|Specifically builds the Spring Boot `.jar`|
|`./gradlew build -x test`|Skips tests during build|

---

## 🧠 Pro Tips

✅ Add `--info` or `--debug` to get more details (e.g. `./gradlew build --info`)  
✅ Use `mvn dependency:analyze` to find unused dependencies  
✅ Run `./gradlew --daemon` for faster builds (it runs Gradle in background)