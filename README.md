# Circuit Breaker Demo Application

This is a sample Spring Boot 3.0 application to demonstrates the **Circuit Breaker** pattern along with the **Retry** mechanism using Resilience4j. The application routes requests to a JSONPlaceholder endpoint and showcases how to handle service failures gracefully.

## Features

- **Circuit Breaker**: Automatically handles failures in external service calls by short-circuiting the request flow after a set threshold of failures.
- **Retry Mechanism**: Attempts to retry the failed request a specified number of times before falling back to an alternative response.

## Endpoints

The application exposes two endpoints that demonstrate the use of both the Circuit Breaker and Retry mechanisms.

### 1. **Circuit Breaker Endpoint**

- **URL**: `/api/circuit-breaker/{integer}`
- **Description**: This endpoint demonstrates the Circuit Breaker pattern. When the application calls the external JSONPlaceholder API, the circuit breaker monitors the success and failure rates. If the API is down or throws an exception (as defined by the configuration), the circuit breaker kicks in and routes the request to a fallback method, ensuring the application remains resilient.

### 2. **Retry Endpoint**

- **URL**: `/api/retry/{integer}`
- **Description**: This endpoint demonstrates the Retry mechanism. If the external API call fails, the application retries for the configured number of attempts. Once all retries are exhausted, the fallback method is invoked to provide a default response.

## How It Works

### Circuit Breaker Pattern

The Circuit Breaker pattern works by attempting to call the underlying JSONPlaceholder API. If the API is down or throws specific exceptions, the circuit breaker intercepts the failure and prevents further calls to the API for a predefined period. During this "open" state, the application directly returns a fallback response without calling the external service. Once the circuit breaker transitions to the "half-open" state, it will test the API again and eventually transition back to the "closed" state if the API recovers.

**Fallback Behavior**: If the API is unreachable or fails with the defined exception set, the fallback method returns a pre-configured default response.

### Retry Mechanism

The Retry mechanism works by attempting to call the JSONPlaceholder API a specific number of times in case of failures. If all retry attempts are exhausted, a fallback method is invoked, providing a predefined response.

**Fallback Behavior**: Once all retry attempts are used, the fallback method takes over and returns a default response.

## Running the Application

1. Clone the repository.
2. Build the application using Maven:
   ```bash
   mvn clean install
