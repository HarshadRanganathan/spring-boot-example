package com.springboot.example.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ExampleHealthIndicator implements HealthIndicator {
    public Health health() {
        if(!isRunning()) return Health.down().withDetail("Error", "Not Available").build();
        return Health.up().build();
    }

    private boolean isRunning() {
        return true;
    }
}
