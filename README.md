# Scalable Inference API

A fault-tolerant, multithreaded inference API using Spring Boot, Kafka, DLQ, and Docker — designed for production-grade performance and reliability.

## 🔧 Tech Stack

- Java 17 + Spring Boot 3
- Apache Kafka + DLQ
- Docker + Docker Compose
- Spring Retry + KafkaListener
- Actuator for health checks

## ✅ Features

- 🔁 Retry logic for transient failures
- 🧯 DLQ fallback for failed inferences
- ⚡ Multithreaded request handling
- 🛠️ Clean producer/consumer architecture
- 🐳 Dockerized (Kafka + App)
- 📊 Observable with Spring Boot Actuator

## 🚀 How to Run

```bash
docker-compose up --build
