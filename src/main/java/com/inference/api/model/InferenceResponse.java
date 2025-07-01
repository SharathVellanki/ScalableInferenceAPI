package com.inference.api.model;

public class InferenceResponse {
    private String result;
    private String threadName;

    public InferenceResponse(String result, String threadName) {
        this.result = result;
        this.threadName = threadName;
    }

    public String getResult() {
        return result;
    }

    public String getThreadName() {
        return threadName;
    }
}
