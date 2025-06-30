Scalable Inference API (Java + Spring Boot)

This project implements a fault-tolerant, multithreaded inference API using:
- Java 17
- Spring Boot
- Kafka for async messaging
- Docker for containerization

Features
- 🔄 Multithreaded request handling
- 💥 Fault recovery using retries and Kafka DLQ
- 📊 Actuator health checks and observability

Run Locally
1. Compile: `javac src/Main.java`
2. Run: `java -cp src Main`


