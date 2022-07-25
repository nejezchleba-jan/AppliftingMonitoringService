package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;
import lombok.Value;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class MonitoringResultResponse {
    int id;
    Instant checkedAt;
    int httpStatusCode;
    String returnedPayload;
    int monitoredEndpointId;

    public static MonitoringResultResponse from(MonitoringResult result) {
        return new MonitoringResultResponse(
                result.getId(),
                result.getCheckedAt(),
                result.getHttpStatusCode(),
                result.getReturnedPayload(),
                result.getMonitoredEndpoint().getId()
        );
    }

    public static List<MonitoringResultResponse> from(List<MonitoringResult> resultList) {
        return resultList
                .stream()
                .map(MonitoringResultResponse::from)
                .collect(Collectors.toList());
    }
}
