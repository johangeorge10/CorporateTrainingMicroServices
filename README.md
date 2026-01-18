# CorpTraining – Microservices Based Training Management System

CorpTraining is a scalable microservices-based backend system built using Spring Boot and Spring Cloud.  
The system exposes REST APIs (JSON) and follows a layered architecture with secure authentication, service discovery, centralized configuration, and inter-service communication.

This project is designed for enterprise-level distributed systems and cloud-native deployment.

---

## 🏗 Architecture Overview

- **Architecture Style:** RESTful Microservices 
- **Communication:** HTTP (JSON APIs)
- **Service Discovery:** Eureka Server
- **API Gateway:** Spring Cloud Gateway (Port: 8090)
- **Config Management:** Spring Cloud Config Server
- **Security:** JWT Authentication
- **Inter-service Communication:** OpenFeign
- **Fault Tolerance:** Fallback mechanisms
- **Data Access:** Spring Data JPA

Each microservice follows a layered structure:

Controller → Service → Repository → Database
DTOs for request/response mapping
Feign Clients for service-to-service calls
Security and Configuration layers


---

## 🚀 Key Features Implemented

### 🔐 Authentication & Security
- JWT-based authentication and authorization
- Token validation across microservices
- Role-based access control using Spring Security
- Secure API access through API Gateway

---

### 🌍 Service Discovery
- Eureka Server for registering and discovering microservices dynamically
- No hardcoded service URLs

---

### 🔁 API Gateway
- Single entry point for all client requests
- Routes traffic to appropriate microservices
- Centralized security and filtering
- Runs on port **8090**

---

### ⚙️ Centralized Configuration
- Spring Cloud Config Server
- All `application.yml` files stored centrally
- Common configuration grouped inside `common.yml`
- Dynamic configuration management without rebuilding services

---

### 🔗 Inter-Service Communication
- OpenFeign clients for calling other microservices
- Load balancing handled automatically
- Clean abstraction for service integration

---

### 🛡 Fault Tolerance
- Fallback mechanisms implemented for Feign clients
- Prevents cascading failures when a service is unavailable

---

### 📦 Layered Code Structure

Each microservice contains:

- **controller** – REST API endpoints
- **service** – Business logic
- **repository** – Database access layer
- **entity** – JPA entities
- **dto** – Request/Response objects
- **client** – Feign clients
- **config** – Application configuration
- **security** – JWT filters and security setup

---

### 📡 Data Format
- All APIs communicate using **JSON**
- Stateless REST services

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Cloud (Eureka, Config Server, Gateway, OpenFeign)
- Spring Security + JWT
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Git

---

## ▶️ How to Run

1. Start **Eureka Server**
2.  Start **Config Server**
3. Start all microservices
4. Start **API Gateway**
5. Access APIs via Gateway:
 http://localhost:8090/{service-route}


---

## 📌 Notes

- This project follows **microservices architecture**, not traditional MVC.
- UI is separated from backend services.
- Designed for scalability, fault tolerance, and enterprise deployment.

