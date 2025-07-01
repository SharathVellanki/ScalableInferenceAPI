package com.inference.api.controller;

import com.inference.api.model.InferenceRequest;
import com.inference.api.model.InferenceResponse;
import com.inference.api.service.InferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InferController {

    @Autowired
    private InferenceService inferenceService;

    @PostMapping("/infer")
    public InferenceResponse infer(@RequestBody InferenceRequest request) {
        return inferenceService.runInference(request.getInput());
    }
}
