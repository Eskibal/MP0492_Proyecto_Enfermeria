# 🏥 Nurse Management API

This is a Backend application developed to manage information and common workflows in a nursing environment. The project is implemented with Java and Maven, uses Hibernate for the persistence layer and follows the typical structure of a Spring Boot application, making it ready to run locally or be deployed on a server.

---

## 📂 Content
+ `src/` Java source code (controllers, services, entities, repositories)
+ `pom.xml` Maven configuration and dependencies
+ `mvnw, mvnw.cmd, .mvn/` Maven Wrapper to run without installing Maven globally
+ Necessary configuration files and scripts to compile and launch the application


## ⚙️ Installation

### Requirements
+ Java 17 or superior
+ Git installed
+ (Optional) Maven installed on local if the wrapper is not going to be used
+ Database compatible with Hibernate (ex. MySQL or PostgreSQL) or use an in-memory configuration for tests

### 1 - Cloning repository
```bash
git clone https://github.com/Eskibal/MP0492_Proyecto_Enfermeria.git
cd MP0492_Proyecto_Enfermeria
```

### 2 - Set environment variables / configuration file
+ Check out the properties file on `src/main/resources` and make the necessary adjustments:
  - The database's URL `spring.datasource.url`
  - User and password `spring.datasource.username` / `spring.datasource.password`
  - Hibernate dialect and JPA setup if nedeed

Example on `application.properties`
```properties
spring.application.name=name
spring.datasource.url=jdbc:mysql://localhost:****/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
### 3 - Install dependencies and compile
+ Using the included wrapper:
```bash
./mvnw clean package
```
+ Or with Maven installed on local:
```bash
mvn clean package
```


## 🖥️ Execution

1. Run with the wrapper (developer mode)
```bash
./mvnw spring-boot:run
```
2. Run the generated JAR (after the package)
```bash
java -jar target/*.jar
```
3. Windows Powershell (if nedeed)
```powershell
mvnw.cmd spring-boot:run
```


## 💡 Use and examples of the API

The application exposes *REST* controllers to manage entities related to nursing activities (patients, visits, vital signs, etc.). Check out the `src/main/java` folder to identify the available controllers and routes.

### Examples of use with `curl`
1. Get Nurse list
```bash
curl -X GET http://localhost:8080/nurse/index
```
2. Create a Nurse (JSON example)
```bash
curl -X POST http://localhost:8080/nurse/new \
  -H "Content-Type: application/json" \
  -d '{"name":"Maria","user":"maria01","password":"1234A","email":"maria01@gmail.com"}'
```
3. Update / Delete / Find Nurse by ID
   - Update `PUT`
   - Delete `DELETE`
   - Find / Read `GET`
```bash
curl -X * http://localhost:8080/nurse/{id}
```
