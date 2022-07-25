package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;

import java.util.List;
import java.util.Optional;

public interface MonitoredEndpointRepository {
    MonitoredEndpoint persist(MonitoredEndpoint endpoint);

    List<MonitoredEndpoint> getAll();

    int deleteById(int monitoredId);

    List<MonitoredEndpoint> getAllForUser(int id);

    Optional<MonitoredEndpoint> getById(int id);
}
