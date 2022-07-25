package cz.jannejezchleba.monitoringservice.domain.usecases.monitoring;

import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;

import java.util.List;

public interface MonitoringResultRepository {
    MonitoringResult persist(MonitoringResult monitoringResult);

    List<MonitoringResult> getTenLastResultsForEndpoint(int endpointId, int userId);

    void deleteAllByEndpointId(int endpointId);
}
