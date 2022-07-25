package cz.jannejezchleba.monitoringservice.data.repositories.interfaces;

import cz.jannejezchleba.monitoringservice.data.entities.MonitoringResultData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudMonitoringResultRepository extends CrudRepository<MonitoringResultData, Integer> {
    List<MonitoringResultData> findTop10ByMonitoredEndpointIdOrderByCheckedAtDesc(int endpointId);

    void deleteAllByMonitoredEndpointId(int endpointId);
}
