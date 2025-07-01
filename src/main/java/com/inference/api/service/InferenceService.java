package com.inference.api.service;

import com.inference.api.model.InferenceResponse;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class InferenceService {

    @Retryable(maxAttempts = 3)
    public InferenceResponse runInference(String input) 
    {
        String threadName = Thread.currentThread().getName();

        // Simulate a slow or failing service (can be replaced by real ML logic)
        if ("fail".equalsIgnoreCase(input)) {
            throw new RuntimeException("Simulated failure for retry");
        }

        String result = "Processed: " + input.toUpperCase();
        return new InferenceResponse(result, threadName);
    }
}
