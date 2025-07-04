package com.inference.api.service;

import com.inference.api.kafka.KafkaProducerService;
import com.inference.api.model.InferenceResponse;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

@Service
public class InferenceService {

    private final KafkaProducerService kafkaProducer;

    public InferenceService(KafkaProducerService kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // 🔁 This will retry up to 3 times on failure
    @Retryable(maxAttempts = 3)
    public InferenceResponse runInference(String input) {
        System.out.println(">> DEBUG: Inside runInference method");
        System.out.println(">> Received input: '" + input + "'");

        // Trigger retry + DLQ if input is "fail"
        if ("fail".equalsIgnoreCase(input.trim())) {
            System.out.println("❌ Simulated failure triggered");
            throw new RuntimeException("Simulated failure for retry");
        }

        // Simulate "successful inference"
        String result = "Processed: " + input.toUpperCase();
        System.out.println("✅ Inference processed normally: " + result);

        kafkaProducer.send(result);

        String threadName = Thread.currentThread().getName();
        return new InferenceResponse(result, threadName);
    }

    // ✅ After retries fail, this handles the recovery
    @Recover
    public InferenceResponse recoverFromFailure(RuntimeException ex, String input) {
        System.out.println("🛑 Retry failed! Sending to DLQ manually: " + input);

        // Still send the failed input to Kafka (DLQ picks it up)
        kafkaProducer.send("PROCESSED: " + input.toUpperCase());

        return new InferenceResponse("Failed to process input after retries", Thread.currentThread().getName());
    }
}
