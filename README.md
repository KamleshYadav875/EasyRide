# 🚗 EasyRide - Book Your Ride

EasyRide is a backend-only ride-hailing platform inspired by Uber. Developed using **Java Spring Boot**, it provides RESTful APIs for core functionalities such as user registration, ride booking, driver management, and trip tracking.
---

## 📚 Features

- 👤 **User Management**
  - Rider & Driver registration
  - JWT-based authentication
  - Role-based access control

- 📍 **Ride Booking**
  - Request a ride
  - Match with nearest driver or Most Rated Drivers
  - Cancel rides

- 🚘 **Driver Management**
  - Driver availability toggles
  - Location updates
  - Ride status updates (Accepted, On Trip, Completed)

- 🗺️ **Trip Tracking**
  - Real-time status and history
  - Trip start/end points

- 🛠️ **Admin Tools**
  - User & driver statistics
  - System health check endpoints

---

## 🧰 Tech Stack

| Tech            | Description                        |
|-----------------|------------------------------------|
| **Java 17+**     | Programming language               |
| **Spring Boot** | Application framework              |
| **Spring Data JPA** | ORM & DB integration          |
| **Spring Security + JWT** | Authentication & Authorization |
| **PostgreSQL** | Database                       |
| **Redis (optional)** | Caching & driver tracking     |
| **Docker**       | Containerization (optional)       |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- MySQL or PostgreSQL running
- (Optional) Redis for driver location caching
