# University System Management (UMS)
The aim of this project is to provide universities with a platform that would help them manage their academic operations more effectively. The project consists of 3 entities:
Users, their Enrollmentsand the Courses.

## Project run
### 1. Clone the repository
`git clone https://github.com/Omirkhon/university-management-system.git`

`cd university-management-system`

### 2. Build
`./mvnw clean install`

### 3. Run
There are two ways. First, we can just run TechnicalTaskApplication class through IDE manually.

Second is to run command:

`./mvnw spring-boot:run `

## Project structure overview
```
src
└── main
    ├── java
    │   └── com.practice.technicaltask
    │       ├── config // Configuration for Spring Security
    │       │   └── jwt
    │       │       ├── JwtAuthenticationFilter.java
    │       │       └── SecurityConfig.java
    │       ├── controller // API Controllers
    │       │   ├── AuthController.java
    │       │   ├── CourseController.java
    │       │   ├── EnrollmentController.java
    │       │   └── UserController.java
    │       ├── dto // dtos that are used in controllers
    │       │   ├── CourseCreateDto.java
    │       │   ├── CourseReadDto.java
    │       │   ├── EnrollmentCreateDto.java
    │       │   ├── EnrollmentReadDto.java
    │       │   ├── UserCreateDto.java
    │       │   └── UserReadDto.java
    │       ├── exceptions // Error Handling and Exception Management
    │       │   ├── ErrorHandler.java
    │       │   ├── ErrorResponse.java
    │       │   ├── ForbiddenException.java
    │       │   └── NotFoundException.java
    │       ├── mapper // mappers, changes data type of entity to dto
    │       │   ├── CourseMapper.java
    │       │   ├── EnrollmentMapper.java
    │       │   └── UserMapper.java
    │       ├── model // Entities
    │       │   ├── Course.java
    │       │   ├── Enrollment.java
    │       │   ├── Role.java
    │       │   └── User.java
    │       ├── repository // repositories
    │       │   ├── CourseRepository.java
    │       │   ├── EnrollmentRepository.java
    │       │   └── UserRepository.java
    │       ├── service // services, main logic of application
    │       │   ├── CourseService.java
    │       │   ├── EnrollmentService.java
    │       │   ├── JwtService.java
    │       │   ├── UserDetailsServiceImpl.java
    │       │   └── UserService.java
    │       └── TechnicalTaskApplication.java // Application's entry point
    └── resources
        ├── application.properties
        └── schema.sql
```
## Example API Endpoints
UserController APIs:

` POST /users ` - create user (only ADMIN can do)

` PATCH /users/{id}` - update user (only ADMIN and the owner of account can do)

` GET /users` - get the list of all users

` GET /users/{id}` - get the user by unique id

` GET /users/name/{name}` - get the user by name

` DELETE /users/{id}` - delete user by id (only ADMIN can do)

CourseController APIs:

` POST /courses ` - create course (only ADMIN can do)

` PATCH /courses/{id}` - update course (only ADMIN can do)

` GET /courses` - get the list of all courses

` GET /courses/{id}` - get the course by unique id

` DELETE /courses/{id}` - delete course by id (only ADMIN can do)

EnrollmentController APIs:

` POST /enrollments?courseId=?&userId=? ` - create enrollment (only ADMIN & TEACHER can do)

` PATCH /enrollments/{id}` - update enrollments (only ADMIN & TEACHER can do)

` GET /enrollments` - get the list of all enrollments

` GET /enrollments/{id}` - get the enrollment by unique id

` GET /enrollments/user/{userId}` - get all user enrollments (can only see own or if the role is ADMIN or TEACHER)

` DELETE /enrollments/{id}` - delete enrollments by id (only ADMIN & TEACHER can do)

AuthController APIs:

` POST /auth/register ` - register the user and then get JWT as response

` POST /auth/login?name=?&password=? ` - login the user by entering name and password and then get JWT as response

## Used Technologies:

• Java 21

• Spring Boot 3

• Spring Security

• Spring Data JPA

• PostgreSQL

• MapStruct

• Lombok

• JWT

## Build and Run commands
1. Build:
   
`./mvnw clean install`

2. Run:

` ./mvnw spring-boot:run`

## Contact information

Amirkhan Turgimbayev

• Gmail: am.turgimbaev@gmail.com

• Phone number: +77787377144

• Telegram: @TheLaddestMate
