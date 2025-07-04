package com.inference.api.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName = "inference-topic";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        System.out.println("📤 [KafkaProducer] Publishing to topic '" + topicName + "' → " + message);
        kafkaTemplate.send(topicName, message);
    }
}
