package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import lombok.Value;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class MonitoredEndpointResponse {
    int id;
    String name;
    String url;
    Instant createdAt;
    Instant lastCheckAt;
    int monitoredInterval;
    int ownerId;

    public static MonitoredEndpointResponse from(MonitoredEndpoint endpoint) {
        return new MonitoredEndpointResponse(
                endpoint.getId(),
                endpoint.getName(),
                endpoint.getUrl(),
                endpoint.getCreatedAt(),
                endpoint.getLastCheckAt(),
                endpoint.getMonitoredInterval(),
                endpoint.getOwner().getId()
        );
    }

    public static List<MonitoredEndpointResponse> from(List<MonitoredEndpoint> endpoints) {
        return endpoints
                .stream()
                .map(MonitoredEndpointResponse::from)
                .collect(Collectors.toList());
    }
}
