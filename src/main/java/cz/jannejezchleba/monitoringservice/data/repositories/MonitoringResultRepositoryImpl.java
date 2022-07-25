package cz.jannejezchleba.monitoringservice.data.repositories;

import cz.jannejezchleba.monitoringservice.data.entities.MonitoringResultData;
import cz.jannejezchleba.monitoringservice.data.repositories.interfaces.CrudMonitoringResultRepository;
import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.MonitoringResultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MonitoringResultRepositoryImpl implements MonitoringResultRepository {
    private final CrudMonitoringResultRepository repository;

    public MonitoringResultRepositoryImpl(CrudMonitoringResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public MonitoringResult persist(MonitoringResult monitoringResult) {
        MonitoringResultData resultData = MonitoringResultData.from(monitoringResult);
        return repository.save(resultData).toDomain();
    }

    @Override
    public List<MonitoringResult> getTenLastResultsForEndpoint(int endpointId, int userId) {
        return repository.findTop10ByMonitoredEndpointIdOrderByCheckedAtDesc(endpointId)
                .stream()
                .map(MonitoringResultData::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByEndpointId(int endpointId) {
        repository.deleteAllByMonitoredEndpointId(endpointId);
    }
}
