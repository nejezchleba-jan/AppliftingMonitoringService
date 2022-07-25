package cz.jannejezchleba.monitoringservice.domain.entities.monitoring;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class MonitoringResult {
    private final int id;
    private final Instant checkedAt;
    private final int httpStatusCode;
    private final String returnedPayload;
    private final MonitoredEndpoint monitoredEndpoint;

    public static MonitoringResultBuilder builder() {
        return new MonitoringResultBuilder();
    }
}
