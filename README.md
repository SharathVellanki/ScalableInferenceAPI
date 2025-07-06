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

## 🧪 Performance

- ✅ Improved average response time by **53%** using in-memory caching and multithreaded request handling.
- Load tested using [`wrk`](https://github.com/wg/wrk) with **100 concurrent requests** over **15 seconds**, validating throughput and latency improvements under stress.
