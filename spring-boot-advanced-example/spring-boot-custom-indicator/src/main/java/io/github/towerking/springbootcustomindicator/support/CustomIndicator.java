package io.github.towerking.springbootcustomindicator.support;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomIndicator implements HealthIndicator {

    // view link: http://localhost:8080/actuator/health to see result
    @Override
    public Health health() {
        Health health;
        health = Health.down()
                .withDetail("down", "down")
                .withDetail("message", "just let it down")
                .build();
        return health;


    }
}
