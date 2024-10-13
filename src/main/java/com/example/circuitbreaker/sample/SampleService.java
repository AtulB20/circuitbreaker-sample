package com.example.circuitbreaker.sample;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SampleService {

    private static final Map<Integer, Todo> cache = new HashMap<>();

    private static int retryCounter = 0;

    @CircuitBreaker(name = "sample-service", fallbackMethod = "getSampleDataFromCache")
    public Todo getSampleDataCircuitBreaker(int index)  {
        Todo result = RestClient.create().get()
                .uri("https://jsonplaceholder.typicode.com/todos/" + index)
                .retrieve()
                .body(Todo.class);

        cache.put(index, result);
        return result;
    }

    @Retry(name = "sample-service", fallbackMethod = "getSampleDataFromCache")
    public Todo getSampleDataWithRetry(int index)  {
        System.out.println("Print retry attempt: " + ++retryCounter);

        Todo result = RestClient.create().get()
                .uri("https://jsonplaceholder.typicode.com/todos/" + index)
                .retrieve()
                .body(Todo.class);

        cache.put(index, result);
        return result;
    }

    private Todo getSampleDataFromCache(int index, Throwable e) {
        System.out.println("Retry attempt exhausted.. ?");

        //return cache.get(index);
        return cache.get(index);
    }



}
