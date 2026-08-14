# Placement Management System

A backend REST API for managing the complete college placement process, including students, skills, companies, jobs, applications, interviews, and final placements.

## Overview

The Placement Management System is a Spring Boot-based application designed to simplify and centralize placement activities within an educational institution.

The system allows placement administrators to:

* Manage student information
* Manage student skills
* Register and manage companies
* Create and manage job opportunities
* Track student applications
* Schedule and manage interviews
* Track final placements
* View placement statistics through a dashboard
* Handle application and resource-related exceptions

## Features

### Student Management

* Add new students
* View student details
* View all students
* Update student information
* Deactivate students
* Store CGPA, branch, resume, and status
* Associate students with multiple skills

### Skill Management

* Create skills
* View skills
* Update skills
* Delete skills
* Prevent duplicate skills
* Associate skills with multiple students

### Company Management

* Add companies
* View company details
* View all companies
* Update company information
* Deactivate companies
* Store company industry and contact information

### Job Management

* Create job opportunities
* Associate jobs with companies
* View available jobs
* Update job details
* Close job opportunities
* Store salary and CGPA eligibility criteria

### Application Management

* Allow students to apply for jobs
* Track application status
* Prevent duplicate applications
* Check student eligibility
* View applications
* Update application status
* Reject applications

### Interview Management

* Schedule interviews
* Associate interviews with applications
* Store interview date, time, and mode
* Track interview results
* Update interview results

### Placement Management

* Record successful placements
* Associate placements with students, companies, and jobs
* Store package amount and placement date
* View placements by student
* View placements by company
* Track placement status

### Dashboard

The dashboard provides an overview of:

* Total students
* Total companies
* Total jobs
* Total applications
* Total interviews
* Total placements

## Technology Stack

| Technology      | Purpose                         |
| --------------- | ------------------------------- |
| Java            | Programming language            |
| Spring Boot     | Backend framework               |
| Spring Web      | REST API development            |
| Spring Data JPA | Database access                 |
| Hibernate       | ORM                             |
| MySQL           | Relational database             |
| Maven           | Dependency management and build |
| Postman         | API testing                     |
| Git & GitHub    | Version control                 |

## Project Architecture

The application follows a layered architecture:

```text
Client / Postman
       |
       v
Controllers
       |
       v
Services
       |
       v
Service Implementations
       |
       +------> Mappers
       |
       v
Repositories
       |
       v
Hibernate / JPA
       |
       v
MySQL Database
```

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── anudip/
    │           └── placement_management_system/
    │
    │               ├── PlacementManagementSystemApplication.java
    │               │
    │               ├── controller/
    │               │   ├── StudentController.java
    │               │   ├── SkillController.java
    │               │   ├── CompanyController.java
    │               │   ├── JobController.java
    │               │   ├── ApplicationController.java
    │               │   ├── InterviewController.java
    │               │   ├── PlacementController.java
    │               │   └── DashboardController.java
    │               │
    │               ├── dto/
    │               │   ├── student/
    │               │   ├── skill/
    │               │   ├── company/
    │               │   ├── job/
    │               │   ├── application/
    │               │   ├── interview/
    │               │   ├── placement/
    │               │   └── dashboard/
    │               │
    │               ├── entity/
    │               │   ├── Student.java
    │               │   ├── Skill.java
    │               │   ├── Company.java
    │               │   ├── Job.java
    │               │   ├── Application.java
    │               │   ├── Interview.java
    │               │   └── Placement.java
    │               │
    │               ├── enums/
    │               │   ├── StudentStatus.java
    │               │   ├── CompanyStatus.java
    │               │   ├── JobStatus.java
    │               │   ├── ApplicationStatus.java
    │               │   ├── InterviewResult.java
    │               │   └── PlacementStatus.java
    │               │
    │               ├── mapper/
    │               │   ├── StudentMapper.java
    │               │   ├── SkillMapper.java
    │               │   ├── CompanyMapper.java
    │               │   ├── JobMapper.java
    │               │   ├── ApplicationMapper.java
    │               │   ├── InterviewMapper.java
    │               │   └── PlacementMapper.java
    │               │
    │               ├── repository/
    │               │   ├── StudentRepository.java
    │               │   ├── SkillRepository.java
    │               │   ├── CompanyRepository.java
    │               │   ├── JobRepository.java
    │               │   ├── ApplicationRepository.java
    │               │   ├── InterviewRepository.java
    │               │   └── PlacementRepository.java
    │               │
    │               ├── service/
    │               │   ├── StudentService.java
    │               │   ├── SkillService.java
    │               │   ├── CompanyService.java
    │               │   ├── JobService.java
    │               │   ├── ApplicationService.java
    │               │   ├── InterviewService.java
    │               │   ├── PlacementService.java
    │               │   ├── DashboardService.java
    │               │   └── impl/
    │               │
    │               └── exception/
    │                   ├── ApplicationNotFound.java
    │                   ├── CompanyNotFound.java
    │                   ├── DuplicateApplication.java
    │                   ├── DuplicateSkillException.java
    │                   ├── IneligibleStudentException.java
    │                   ├── JobNotFoundException.java
    │                   ├── StudentNotFoundException.java
    │                   ├── ResourceNotFound.java
    │                   └── GlobalExceptionHandler.java
    │
    └── resources/
        └── application.properties
```

## Entity Relationships

The main entities are related as follows:

```text
                    ┌──────────────┐
                    │   Company    │
                    └──────┬───────┘
                           │
                         1:N
                           │
                           v
                    ┌──────────────┐
                    │     Job      │
                    └──────┬───────┘
                           │
                         1:N
                           │
                           v
┌──────────────┐     ┌──────────────┐
│   Student    │────<│ Application  │
└──────┬───────┘     └──────┬───────┘
       │                    │
       │ N:M                │ 1:N
       v                    v
┌──────────────┐      ┌──────────────┐
│    Skill     │      │   Interview  │
└──────────────┘      └──────────────┘

       Student
          │
         1:1
          │
          v
   ┌──────────────┐
   │  Placement   │
   └──────┬───────┘
          │
          ├── Company
          │
          └── Job
```

### Main Relationships

* A **Company** can have multiple Jobs.
* A **Student** can submit multiple Applications.
* A **Job** can receive multiple Applications.
* An **Application** can have multiple Interviews.
* A **Student** can have multiple Skills.
* A **Skill** can belong to multiple Students.
* A Student can have one final Placement in the current design.
* A Company can have multiple Placements.
* A Job can have multiple Placements.

## Database Configuration

Create the MySQL database:

```sql
CREATE DATABASE placement_management;
```

Configure `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/placement_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Replace `YOUR_PASSWORD` with your MySQL password.

The application uses Hibernate's `ddl-auto=update` configuration to create/update database tables based on the entity mappings.

## Running the Application

### 1. Clone the repository

```bash
git clone <your-github-repository-url>
```

### 2. Navigate to the project

```bash
cd placement_management_system
```

### 3. Configure MySQL

Make sure MySQL is running and create the database:

```sql
CREATE DATABASE placement_management;
```

### 4. Configure credentials

Update:

```text
src/main/resources/application.properties
```

with your MySQL username and password.

### 5. Run using Maven

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

The application will run at:

```text
http://localhost:8080
```

## API Endpoints

### Students

| Method | Endpoint                        | Description        |
| ------ | ------------------------------- | ------------------ |
| POST   | `/api/students`                 | Create student     |
| GET    | `/api/students`                 | Get all students   |
| GET    | `/api/students/{id}`            | Get student        |
| PUT    | `/api/students/{id}`            | Update student     |
| PATCH  | `/api/students/{id}/deactivate` | Deactivate student |

### Skills

| Method | Endpoint           | Description    |
| ------ | ------------------ | -------------- |
| POST   | `/api/skills`      | Create skill   |
| GET    | `/api/skills`      | Get all skills |
| GET    | `/api/skills/{id}` | Get skill      |
| PUT    | `/api/skills/{id}` | Update skill   |
| DELETE | `/api/skills/{id}` | Delete skill   |

### Companies

| Method | Endpoint                         | Description        |
| ------ | -------------------------------- | ------------------ |
| POST   | `/api/companies`                 | Create company     |
| GET    | `/api/companies`                 | Get all companies  |
| GET    | `/api/companies/{id}`            | Get company        |
| PUT    | `/api/companies/{id}`            | Update company     |
| PATCH  | `/api/companies/{id}/deactivate` | Deactivate company |

### Jobs

| Method | Endpoint               | Description  |
| ------ | ---------------------- | ------------ |
| POST   | `/api/jobs`            | Create job   |
| GET    | `/api/jobs`            | Get all jobs |
| GET    | `/api/jobs/{id}`       | Get job      |
| PUT    | `/api/jobs/{id}`       | Update job   |
| PATCH  | `/api/jobs/{id}/close` | Close job    |

### Applications

| Method | Endpoint                        | Description          |
| ------ | ------------------------------- | -------------------- |
| POST   | `/api/applications`             | Create application   |
| GET    | `/api/applications`             | Get all applications |
| GET    | `/api/applications/{id}`        | Get application      |
| PUT    | `/api/applications/{id}`        | Update application   |
| PATCH  | `/api/applications/{id}/reject` | Reject application   |

### Interviews

| Method | Endpoint                      | Description             |
| ------ | ----------------------------- | ----------------------- |
| POST   | `/api/interviews`             | Schedule interview      |
| GET    | `/api/interviews`             | Get all interviews      |
| GET    | `/api/interviews/{id}`        | Get interview           |
| PUT    | `/api/interviews/{id}`        | Update interview        |
| PATCH  | `/api/interviews/{id}/result` | Update interview result |

### Placements

| Method | Endpoint                              | Description              |
| ------ | ------------------------------------- | ------------------------ |
| POST   | `/api/placements`                     | Create placement         |
| GET    | `/api/placements`                     | Get all placements       |
| GET    | `/api/placements/{id}`                | Get placement            |
| GET    | `/api/placements/student/{studentId}` | Get student's placement  |
| GET    | `/api/placements/company/{companyId}` | Get company's placements |
| PUT    | `/api/placements/{id}`                | Update placement         |

### Dashboard

| Method | Endpoint         | Description              |
| ------ | ---------------- | ------------------------ |
| GET    | `/api/dashboard` | Get placement statistics |

## Example API Requests

### Create Student

```http
POST /api/students
Content-Type: application/json
```

```json
{
    "name": "Era Todkar",
    "email": "era@example.com",
    "phone": "9876543210",
    "cgpa": 8.7,
    "branch": "Computer Engineering",
    "resume": "era_resume.pdf"
}
```

### Create Company

```http
POST /api/companies
Content-Type: application/json
```

```json
{
    "name": "TCS",
    "location": "Pune",
    "industry": "Information Technology",
    "contactEmail": "hr@tcs.com",
    "contactPhone": "0201234567"
}
```

### Create Job

```http
POST /api/jobs
Content-Type: application/json
```

```json
{
    "title": "Java Developer",
    "description": "Backend development using Java and Spring Boot",
    "location": "Pune",
    "salary": 600000,
    "eligibilityCgpa": 7.0,
    "companyId": 1
}
```

### Create Application

```http
POST /api/applications
Content-Type: application/json
```

```json
{
    "studentId": 1,
    "jobId": 1,
    "status": "APPLIED"
}
```

### Create Interview

```http
POST /api/interviews
Content-Type: application/json
```

```json
{
    "applicationId": 1,
    "interviewDate": "2026-08-20",
    "interviewTime": "10:30:00",
    "mode": "ONLINE",
    "result": "PENDING"
}
```

### Create Placement

```http
POST /api/placements
Content-Type: application/json
```

```json
{
    "studentId": 1,
    "companyId": 1,
    "jobId": 1,
    "packageAmount": 600000,
    "placementDate": "2026-08-25",
    "status": "PLACED"
}
```

## Exception Handling

The application uses custom exceptions and a global exception handler.

### Custom Exceptions

* `StudentNotFoundException`
* `CompanyNotFound`
* `JobNotFoundException`
* `ApplicationNotFound`
* `DuplicateApplication`
* `DuplicateSkillException`
* `IneligibleStudentException`
* `ResourceNotFound`

### HTTP Status Codes

| Exception                   |            Status |
| --------------------------- | ----------------: |
| Resource not found          |   `404 NOT FOUND` |
| Duplicate application/skill |    `409 CONFLICT` |
| Ineligible student          | `400 BAD REQUEST` |

`GlobalExceptionHandler` uses `@RestControllerAdvice` to provide centralized exception handling across the application.

## Testing

The REST APIs can be tested using Postman.

Recommended testing order:

```text
1. Create Skills
       ↓
2. Create Students
       ↓
3. Create Companies
       ↓
4. Create Jobs
       ↓
5. Create Applications
       ↓
6. Create Interviews
       ↓
7. Create Placements
       ↓
8. Check Dashboard
```

The application can also be tested by deliberately sending invalid IDs and duplicate applications to verify the custom exception handling.

## Git Workflow

Typical development workflow:

```bash
git status

git add .

git commit -m "Add placement management features"

git push origin main
```

To retrieve the latest changes:

```bash
git pull origin main
```

## Future Improvements

Potential future enhancements include:

* JWT-based authentication and authorization
* Separate Admin and Student roles
* Password-based login
* Resume file upload
* Advanced search and filtering
* Pagination and sorting
* Email notifications
* Interview reminders
* Placement analytics and charts
* Swagger/OpenAPI documentation
* Docker deployment
* Frontend using React
* Automated unit and integration testing
* Deployment to a cloud platform

## Project Status

The project currently provides the core backend architecture for a Placement Management System, including entity management, REST APIs, database persistence, DTOs, mappers, service layers, repositories, dashboard statistics, and centralized exception handling.

## Author

Shivani Nikam

Placement Management System — Spring Boot REST API
