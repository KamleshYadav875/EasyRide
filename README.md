# 🚗 EasyRide – Ride-Booking Backend System

**EasyRide** is a scalable and modular backend system for a ride-hailing application, inspired by platforms like Uber and Ola. Built with **Spring Boot**, **PostgreSQL with PostGIS**, and clean architectural practices, it supports user authentication, ride matching, digital payments, fare strategies, and wallet systems — all using extensible strategy patterns for maximum flexibility.

---

## 📖 Project Description

EasyRide simulates real-world ride-hailing workflows with a focus on clean architecture and extensibility. It supports:
- JWT-secured authentication for Riders, Drivers, and Admins.
- Geo-location-based ride request & driver matching using PostGIS.
- OTP-secured ride start.
- Digital wallet integration with full transaction tracking.
- Flexible fare calculation strategies (base fare, surge pricing).
- Multiple payment modes (cash, wallet) using Strategy Pattern.
- Rating and feedback between riders and drivers.

---

## 🧠 Key Design Patterns

- **Strategy Pattern**: Used for fare calculation, payment processing, and driver matching.
- **Builder Pattern**: For flexible object creation (e.g., Wallet, Ride).
- **DTO + Mapper Layer**: Ensures clear separation between domain and API models.
- **Service Layer Architecture**: Clean layering between controller, service, and repository.

---

## 🧰 Tech Stack

| Category        | Technology                        |
|-----------------|------------------------------------|
| Language        | Java 17                            |
| Framework       | Spring Boot                        |
| Database        | PostgreSQL with PostGIS            |
| ORM             | Hibernate (JPA)                    |
| Security        | Spring Security + JWT              |
| Build Tool      | Maven                              |
| Geolocation     | JTS (Java Topology Suite), SRID 4326|
| API Style       | RESTful                            |
| Dev Tools       | Lombok, MapStruct, Swagger UI      |

---

## 🔐 Authentication & Authorization

- Role-based access: `ADMIN`, `DRIVER`, `RIDER`
- JWT token authentication for secure access
- Passwords encrypted using BCrypt

---

## 👤 User Management

- `User` is a base entity for `Rider` and `Driver`
- Admin users can onboard and manage drivers
- Riders register independently via exposed API

---

## 📍 Geolocation & Ride Matching

- Locations stored as `Point` (PostGIS geometry, SRID 4326)
- Indexed for fast spatial querying
- **Driver Matching Strategies**:
  - `NearestDriverStrategy`
  - `HighestRatedDriverStrategy`

---

## 💸 Fare Calculation

- Distance calculated using the **Haversine Formula**
- Base Fare + Per KM fare, configurable
- **Fare Strategies**:
  - `DefaultFareCalculationStrategy`
  - `SurgePricingFareCalculationStrategy`

---

## 💼 Wallet & Payments

- Each user has a digital wallet (`Wallet` entity)
- Transactions logged via `WalletTransaction` entity
- Supports multiple **Payment Strategies**:
  - `CashPaymentStrategy`
  - `WalletPaymentStrategy`
- Auto-deduction on ride completion

---

## 🔄 Ride Flow

1. **Rider** sends a ride request
2. **System** matches rider with suitable driver
3. **Driver** accepts and starts ride using OTP
4. **Fare** is calculated on completion
5. **Payment** is processed (Cash / Wallet)
6. **Rating** and feedback exchanged

---

## ⭐ Rating & Feedback

- Bi-directional ratings (`Rating` entity)
- Comments & rating value stored
- Ratings influence future ride matching

---

## 🧪 Utilities

- `GeometryUtil`: Converts DTOs to PostGIS `Point` objects
- `DistanceService`: Calculates distance between two geospatial points

---

http://localhost:8080/swagger-ui/index.html


✅ Future Enhancements
  - WebSocket-based live tracking
  - UPI/Card payment integration
  - Admin dashboard UI
  - Scheduled rides
  - Advanced fraud detection (e.g., location spoofing)

👨‍💻 Author
Kamlesh Yadav
LIMS Developer | Java Backend Engineer | Spring Boot Enthusiast

