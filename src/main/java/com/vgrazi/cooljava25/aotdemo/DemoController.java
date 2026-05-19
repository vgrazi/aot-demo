package com.vgrazi.cooljava25.aotdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;
import module java.base;

@RestController
 class DemoController {
    public static final int INDEX = 30;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        long requestStart = System.nanoTime();

        // Make the work heavier so we can see real timing difference
        long fibResult = calculateFibonacci(INDEX);   // Increased from 45

        long requestTimeNs = System.nanoTime() - requestStart;

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.Instant.now().toString());
        response.put("requestProcessingTimeNs", String.format("%d", requestTimeNs));
        response.put("status", "success");
        response.put("message", "AOT Demo - Java 25");
        response.put("note", "Compare startup + first request time with & without AOT");
        response.put("fibonacci(" + INDEX + ")", fibResult);

        try {
            String json = objectMapper.writeValueAsString(response);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error converting to JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Heavier Fibonacci for better demo timing
    private long calculateFibonacci(int n) {
        if (n <= 1) return n;
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}
