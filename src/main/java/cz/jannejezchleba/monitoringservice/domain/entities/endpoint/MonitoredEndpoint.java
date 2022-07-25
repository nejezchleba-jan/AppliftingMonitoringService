package cz.jannejezchleba.monitoringservice.domain.entities.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class MonitoredEndpoint {
    private final int id;
    private final String name;
    private final String url;
    private final Instant createdAt;
    private final Instant lastCheckAt;
    private final int monitoredInterval;
    private final User owner;

    public MonitoredEndpoint rename(String newName) {
        return MonitoredEndpoint.builder()
                .id(id)
                .name(newName)
                .url(url)
                .createdAt(createdAt)
                .lastCheckAt(lastCheckAt)
                .monitoredInterval(monitoredInterval)
                .owner(owner)
                .build();
    }

    public MonitoredEndpoint changeMonitorInterval(int newInterval) {
        return MonitoredEndpoint.builder()
                .id(id)
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .lastCheckAt(lastCheckAt)
                .monitoredInterval(newInterval)
                .owner(owner)
                .build();
    }

    public static MonitoredEndpointBuilder builder() {
        return new MonitoredEndpointBuilder();
    }
}
