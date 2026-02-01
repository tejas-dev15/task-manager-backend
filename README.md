# 🗂️ Task Manager Backend Application

A backend Task Manager application built using Spring Boot, following a clean layered architecture and secured with Spring Security.  
The project supports full CRUD operations, role-based authorization, and uses MongoDB Atlas (cloud) for data storage.

---

## 🚀 Features

- User & Admin roles
- Authentication and Authorization using Spring Security
- Role-based access control
- CRUD operations for task management
- Clean layered architecture
- MongoDB Atlas (Cloud Database)
- JWT-based authentication (planned)

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- MongoDB (NoSQL)
- MongoDB Atlas (Cloud)
- Maven

---

## 📂 Project Architecture
```
src/main/java/com/example/Task_Manager
│
├── Controller
│ ├── AdminController
│ ├── PublicController
│ ├── TaskController
│ └── UserController
│
├── Entity
│ ├── Task
│ └── User
│
├── Repository
│ ├── Task_Repository
│ └── User_Repository
│
├── Service
│ ├── TaskService
│ ├── UserService
│ └── UserDetailsServiceImpl
│
├── SecurityConfig
│ └── Spring_security
│
└── TaskManagerApplication
```

---

## ⚙️ Setup & Run Locally

### 1. Clone the repository
```
git clone https://github.com/tejas-dev15/task-manager-backend.git
```


### 2. Configure MongoDB
Create `application.properties` and add:
```
spring.data.mongodb.uri=YOUR_MONGODB_URI
```


### 3. Run the application
```
mvn spring-boot:run
```


---

## 📌 API Access

- Public APIs → Accessible without authentication
- Admin APIs → Restricted to ADMIN role
- User APIs → Restricted to USER role

---

## 🔮 Future Improvements

- JWT-based authentication
- DTO implementation
- Global exception handling
- API documentation (Swagger)

---

## 🤝 Author

Tejas  
Backend Developer | Spring Boot | MongoDB

⭐ If you like this project, consider giving it a star!
