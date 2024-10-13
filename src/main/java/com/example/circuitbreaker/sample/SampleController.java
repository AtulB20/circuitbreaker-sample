package com.example.circuitbreaker.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/api/circuit-breaker/{index}")
    public Todo getSampleData(@PathVariable int index) {
        return sampleService.getSampleDataCircuitBreaker(index);
    }

    @GetMapping("/api/retry/{index}")
    public Todo getSampleDataRetry(@PathVariable int index) {
        return sampleService.getSampleDataWithRetry(index);
    }

}
