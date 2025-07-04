package com.inference.api.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DLQConsumerService {

    @KafkaListener(topics = "${kafka.topic.dlq}", groupId = "dlq-group")
    public void listenDLQ(ConsumerRecord<String, String> record) {
        System.out.println("🛑 [DLQConsumer] Received from DLQ: " + record.value());
    }
}
