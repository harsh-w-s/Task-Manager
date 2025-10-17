# 📝 Task Manager Backend

## 🚀 Overview
This is the **Spring Boot backend** for a Task Manager application.  
It provides **REST APIs** to manage tasks, track changes via audit logs, and handle **authentication & role-based access**.  
Built with **Spring Boot**, **JPA/Hibernate**, and **MySQL**, secured with **JWT**.  

---

## ✨ Features
- ✅ **Task Management**: Create, update, delete, and fetch tasks with pagination.  
- 📜 **Audit Logs**: Tracks all task actions (create/update/delete).  
- 🔒 **Authentication & Authorization**: JWT-based authentication with roles.  
- 🗄️ **Data Persistence**: MySQL database using JPA/Hibernate.  
- ⚠️ **Validation & Error Handling**: Input validation and structured API responses.  

---

## 🛠️ Technologies Used
- Java 17  
- Spring Boot  
- Spring Security (JWT)  
- JPA / Hibernate  
- MySQL  
- Maven  

---

## 📡 API Endpoints

### Tasks
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/tasks` | Fetch all tasks (paginated) |
| POST   | `/api/tasks` | Create a new task |
| PUT    | `/api/tasks/{id}` | Update a task by ID |
| DELETE | `/api/tasks/{id}` | Delete a task by ID |

### Audit Logs
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/logs` | Fetch all audit logs (Admin only) |

---

## ⚡ Getting Started

### Prerequisites
- Java 17+ ☕  
- Maven 📦  
- MySQL 🗄️  

### Setup
1. Clone the repo:  
   ```bash
   git clone <your-repo-url>
   cd task-manager-backend
