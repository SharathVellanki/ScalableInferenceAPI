package com.inference.api.service;

import com.inference.api.kafka.KafkaProducerService;
import com.inference.api.model.InferenceResponse;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InferenceService {

    private final KafkaProducerService kafkaProducer;

    // In-memory cache to store inference results
    private final Map<String, InferenceResponse> cache = new ConcurrentHashMap<>();

    public InferenceService(KafkaProducerService kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Retryable(maxAttempts = 3)
    public InferenceResponse runInference(String input) {
        String normalizedInput = input.trim().toLowerCase();

        System.out.println(">> Checking cache for key: '" + normalizedInput + "'");

        if (cache.containsKey(normalizedInput)) {
            System.out.println("🧠 Cache hit for: " + normalizedInput);
            return cache.get(normalizedInput);
        }

        System.out.println(">> DEBUG: Inside runInference method");
        System.out.println(">> Received input: '" + input + "'");

        if ("fail".equalsIgnoreCase(normalizedInput)) {
            System.out.println("❌ Simulated failure triggered");
            throw new RuntimeException("Simulated failure for retry");
        }

        String result = "Processed: " + normalizedInput.toUpperCase();

        kafkaProducer.send(result);

        String threadName = Thread.currentThread().getName();
        InferenceResponse response = new InferenceResponse(result, threadName);

        // Store in cache
        cache.put(normalizedInput, response);

        return response;
    }
}
