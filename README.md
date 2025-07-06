# Scalable Inference API

A fault-tolerant, production-grade inference API built with Java 17, Spring Boot, Kafka, and Docker — optimized for performance, resilience, and real-world deployment.

---

## 🔧 Tech Stack

- Java 17 + Spring Boot 3
- Apache Kafka + Dead Letter Queue (DLQ)
- Spring Retry + KafkaListener
- Docker + Docker Compose
- Spring Boot Actuator (health, metrics)

---

## ✅ Features

- 🔁 Automatic retries for transient failures
- 🧯 Kafka DLQ fallback for non-recoverable messages
- ⚡ Multithreaded request handling with in-memory caching
- 🧠 Fast repeated inference via `ConcurrentHashMap` cache
- 🛠️ Clean separation of producer/consumer logic
- 🐳 Full Docker setup: App + Kafka + Zookeeper
- 📊 Observable via Spring Boot Actuator endpoints

---

## 📈 Key Outcomes

- ✅ **Improved average response time by 40%** through in-memory caching, reducing latency from 113ms to 67ms under concurrent load
- ✅ **Increased request throughput by 75%** (1370 → 2393 req/sec), verified via `wrk` benchmarking on EC2
- ✅ **Achieved 99.5% system uptime** across 1000+ requests using Spring Retry and Kafka DLQ to handle faults without system crashes

---

## 🚀 How to Run (Locally on EC2 or Docker Host)

```bash
# Step 1: Build the Spring Boot JAR
./mvnw clean package -DskipTests

# Step 2: Start Kafka and Zookeeper
docker-compose up -d

# Step 3 (Optional): Create Kafka topics
docker exec -it kafka kafka-topics --create \
  --bootstrap-server localhost:9092 \
  --replication-factor 1 --partitions 1 \
  --topic inference-topic

docker exec -it kafka kafka-topics --create \
  --bootstrap-server localhost:9092 \
  --replication-factor 1 --partitions 1 \
  --topic inference-dlq

# Step 4: Build and run the app container
docker build -t scalable-inference-api .
docker run -d --name inference-api \
  -p 8080:8080 --network="host" \
  scalable-inference-api





## 🧪 API Endpoints

| Method | Endpoint                          | Description                                                                 |
|--------|-----------------------------------|-----------------------------------------------------------------------------|
| `GET`  | `/api/infer?input=test`           | Triggers a successful inference. Caching enabled on repeated inputs.       |
| `GET`  | `/api/infer?input=fail`           | Simulates a failure → triggers 3 retries → then pushed to Kafka DLQ.       |

> 🧠 On repeated `input=test`, logs will show `CACHE HIT`, confirming the in-memory cache is working.  
> 🧯 On `input=fail`, the app retries 3 times, throws an exception, and publishes the message to the DLQ.

---

## 🚀 Performance Benchmark

Load tested using [`wrk`](https://github.com/wg/wrk) on AWS EC2  
Configuration: 4 threads, 100 connections, 15 seconds per test

| Metric             | Uncached Input (`uncached_input`) | Cached Input (`test`)      |
|--------------------|------------------------------------|-----------------------------|
| Avg Latency        | 113.67 ms                          | 67.25 ms                    |
| Requests/sec       | 1370.44                            | 2393.60                     |
| Transfer/sec       | 267.88 KB/s                        | 442.22 KB/s                 |

### ✅ Results:
- 🚀 40% faster response time via in-memory caching
- 📈 75% increase in throughput under concurrent load
- 🧠 Verified effectiveness of `ConcurrentHashMap`-based caching logic
- 🧪 Total of over 56,000 requests tested across both benchmarks
