# Hygiene Monitoring System — Backend

A full stack smart city hygiene monitoring system built with Spring Boot 3, Java 21, and MySQL.
**Live App:** https://hygienemonitoring.netlify.app/

## Features

- JWT-based authentication with role-based access (Citizen / Official)
- Citizens can raise hygiene complaints — automatically routed to ward officials
- Officials can assign field crews and resolve complaints
- IoT bin level simulator using Spring Scheduler — auto-increments every 60 seconds
- Real-time notifications when bin fill level crosses 80%
- RESTful API with 15+ endpoints

## Tech Stack

- Java 21, Spring Boot 3.3
- Spring Security + JWT
- Spring Data JPA + Hibernate
- MySQL 8.0
- Maven

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register as Citizen or Official |
| POST | /api/auth/login | Login and receive JWT token |
| POST | /api/complaints | Citizen raises a complaint |
| GET | /api/complaints/my | Citizen views own complaints |
| GET | /api/complaints/ward | Official views ward complaints |
| PUT | /api/complaints/{id}/assign | Official assigns crew |
| PUT | /api/complaints/{id}/resolve | Official resolves complaint |
| GET | /api/bins/ward/{wardNumber} | Get bins for a ward |
| GET | /api/notifications/me | Get notifications |

## Project Structure
src/main/java/com/hygienetracker/backend/

├── controller/    — REST API controllers

├── service/       — Business logic

├── repository/    — Database access

├── model/         — JPA entities

├── security/      — JWT filter, config

└── dto/           — Request/response objects

## Setup Instructions
> Note: This backend is already deployed and running on Railway — the steps below are only for running it locally.

1. Install Java 21 and MySQL 8.0
2. Create database: `CREATE DATABASE hygiene_tracker;`
3. Clone the repository: `git clone https://github.com/KRITHI1022/hygiene-tracker-backend.git`
4. Navigate to the project folder: `cd hygiene-tracker-backend`
5. Update `src/main/resources/application.properties` with your MySQL password
6. Run the app: `mvn spring-boot:run`
7. Backend starts on `http://localhost:8080`

## Frontend Repository

[hygiene-tracker-frontend](https://github.com/KRITHI1022/hygiene-tracker-frontend)
