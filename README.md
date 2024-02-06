# Employee Management System

The Employee Management System is a microservices-based application developed with Spring Boot, aiming to streamline HR processes and facilitate employee-related tasks.

## Table of Contents

- [Project Structure](#project-structure)
- [System Architecture](#system-architecture)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Deployment](#deployment)
## Project Structure

```plaintext
employee-management-system/
|-- employee-service/
|   |-- src/
|   |   |-- main/
|   |       |-- java/
|   |       |   |-- com/
|   |       |       |-- kal/
|   |       |           |-- employeeservice/
|   |       |               |-- controller/
|   |       |               |-- model/
|   |       |               |-- repository/
|   |       |               |-- service/
|   |       |-- resources/
|   |           |-- application.properties
|   |
|   |-- Dockerfile
|
|-- attendance-service/
|   |-- ... (similar structure as employee-service)
|
|-- performance-review-service/
|   |-- ... (similar structure as employee-service)
|
|-- api-gateway/
|   |-- ... (similar structure as employee-service)
|
|-- service-registry/
|   |-- ... (similar structure as employee-service)
|
|-- docker-compose.yml
|-- README.md
```
## System Architecture

The system follows a microservices architecture with Spring Cloud components:

```plaintext
                         +---------------------+
                         |      API Gateway    |
                         +----------+----------+
                                    |
                                    |
        +---------------------------v---------------------------+
        | Employee Service (Spring Boot Microservice)            |
        |   - Employee CRUD Operations                           |
        |   - Database: Employee Table                           |
        +---------------------------+---------------------------+
                                    |
                                    |
        +---------------------------v---------------------------+
        | Attendance Service (Spring Boot Microservice)          |
        |   - Attendance Tracking Operations                     |
        |   - Database: Attendance Table                         |
        +---------------------------+---------------------------+
                                    |
                                    |
        +---------------------------v---------------------------+
        | Performance Review Service (Spring Boot Microservice)   |
        |   - Performance Review Operations                      |
        |   - Database: PerformanceReview Table                   |
        +--------------------------------------------------------+
```
- **employee-service**, **attendance-service**, and **performance-review-service** contain the microservices source code.
- **api-gateway** is the API Gateway service.
- **service-registry** is the Eureka Service Registry.
- **docker-compose.yml** defines the Docker Compose configuration for the entire system.

## Getting Started
To run the Employee Management System locally, follow these steps:

1. Clone the repository:
```bash
git clone https://github.com/your-username/employee-management-system.git
```
2. Navigate to the project root:
```bash
cd employee-management-system
```
3. Start the system using Docker Compose:
```bash
docker-compose up
```
4. Access the services at:

    - Employee Service: http://localhost:8081
    - Attendance Service: http://localhost:8082
    - Performance Review Service: http://localhost:8083
    - API Gateway: http://localhost:8080
    - Eureka Service Registry: http://localhost:8761

## API Documentation
API documentation for each service can be found at the following endpoints:

- Employee Service: http://localhost:8081/swagger-ui.html
- Attendance Service: http://localhost:8082/swagger-ui.html
- Performance Review Service: http://localhost:8083/swagger-ui.html
- API Gateway: http://localhost:8080/swagger-ui.html

## Deployment
For deployment, consider using container orchestration tools like Kubernetes or Docker Swarm. Adjust environment variables and configurations accordingly for production deployment.
