# Hospital Management System

A cloud-based Hospital Management System developed using Spring Boot and MySQL. This project helps manage hospital operations such as patient records, doctor information, appointments, departments, and insurance details through secure REST APIs.

The application is secured using JWT Authentication and Role-Based Access Control, ensuring that only authorized users can access specific features. The project is deployed on Render and connected to a cloud-hosted MySQL database on Aiven.

## Key Features

- Secure User Registration and Login
- JWT-based Authentication and Authorization
- Role-Based Access Control (Admin, Doctor, Patient)
- Patient Management
- Doctor Management
- Department Management
- Appointment Scheduling
- Insurance Management
- RESTful API Architecture
- Cloud Database Integration
- Dockerized Deployment

## Technology Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Docker
- Render
- Aiven Cloud Database

## Project Highlights

- Developed a secure backend application following REST API principles.
- Implemented JWT authentication for stateless security.
- Designed database relationships using JPA and Hibernate.
- Connected the application to a cloud-hosted MySQL database.
- Containerized the application using Docker.
- Deployed the application on Render for public access.

## Live API Documentation

Swagger UI:

https://hospital-management-system-oda7.onrender.com/swagger-ui/index.html

## How to Test

1. Open the Swagger UI link.
2. Create a user using the Signup API.
3. Login using the Login API.
4. Copy the generated JWT token.
5. Click the **Authorize** button in Swagger and paste the token.
6. Access protected APIs such as Patients, Doctors, Appointments, and Departments.

## Deployment

- Backend Hosting: Render
- Database: Aiven MySQL
- Containerization: Docker

## Author
**Rahul Bhatt**
 B.Tech Graduate | Java Backend Developer
