package cz.jannejezchleba.monitoringservice;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.user.User;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public final class TestEntityGenerator {
    private static final Random rand = new Random();

    public static MonitoredEndpoint generateEndpoint() {
        return new MonitoredEndpoint(
                rand.nextInt(),
                "test",
                "http://test.com",
                Instant.now(),
                null,
                rand.nextInt(),
                generateUser()
        );
    }

    public static MonitoredEndpoint generateEndpointWithOwner(User owner) {
        return new MonitoredEndpoint(
                rand.nextInt(),
                "test",
                "http://test.com",
                Instant.now(),
                null,
                rand.nextInt(),
                owner
        );
    }

    public static User generateUser() {
        return new User(
                rand.nextInt(),
                "test",
                "Tester",
                UUID.randomUUID().toString()
        );
    }
}
