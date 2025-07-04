package com.inference.api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    // Listen to messages from the main Kafka topic
    @KafkaListener(topics = "${kafka.topic.main}", groupId = "inference-group")
    public void listen(String message) {
        System.out.println(">> Received from Kafka: " + message);

        // Simulate a processing failure (this will trigger DLQ after retries)
        if (message.contains("FAILURE_SIM")) {
            throw new RuntimeException("Simulated consumer failure for DLQ test");
        }

        // Otherwise, pretend to process the message normally
        System.out.println(">> Successfully processed: " + message);
    }
}
