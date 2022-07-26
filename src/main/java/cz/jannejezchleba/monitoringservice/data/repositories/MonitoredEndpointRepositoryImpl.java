package cz.jannejezchleba.monitoringservice.data.repositories;

import cz.jannejezchleba.monitoringservice.data.entities.MonitoredEndpointData;
import cz.jannejezchleba.monitoringservice.data.repositories.interfaces.CrudEndpointRepository;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MonitoredEndpointRepositoryImpl implements MonitoredEndpointRepository {
    private final CrudEndpointRepository repository;

    public MonitoredEndpointRepositoryImpl(CrudEndpointRepository repository) {
        this.repository = repository;
    }

    @Override
    public MonitoredEndpoint persist(MonitoredEndpoint endpoint) {
        MonitoredEndpointData endpointData = MonitoredEndpointData.from(endpoint);
        return repository.save(endpointData).toDomain();
    }

    @Override
    public List<MonitoredEndpoint> getAll() {
        return repository.findAll()
                .parallelStream()
                .map(MonitoredEndpointData::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public int deleteById(int monitoredId) {
        repository.deleteById(monitoredId);
        return monitoredId;
    }

    @Override
    public List<MonitoredEndpoint> getAllForUser(int userId) {
        return repository.getAllByOwnerId(userId)
                .parallelStream()
                .map(MonitoredEndpointData::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MonitoredEndpoint> getById(int id) {
        return repository.findById(id).map(MonitoredEndpointData::toDomain);
    }

    @Override
    public boolean existsByNameAndOwnerId(String name, int userId) {
        return repository.existsByNameAndOwnerId(name, userId);
    }
}
