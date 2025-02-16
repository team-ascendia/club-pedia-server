package clubpedia.in.net.clubpedia.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;


@Tag(name="헬스체크")
@RestController
@RequestMapping("/health")
public class HealthController implements HealthIndicator {

    @Override
    @GetMapping
    public Health health() {
        return Health.up().withDetail("status", "UP").build();
    }
}