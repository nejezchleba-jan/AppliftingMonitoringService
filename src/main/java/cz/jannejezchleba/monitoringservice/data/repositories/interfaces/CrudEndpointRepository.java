package cz.jannejezchleba.monitoringservice.data.repositories.interfaces;

import cz.jannejezchleba.monitoringservice.data.entities.MonitoredEndpointData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudEndpointRepository extends CrudRepository<MonitoredEndpointData, Integer> {
    List<MonitoredEndpointData> getAllByOwnerId(int userId);

    List<MonitoredEndpointData> findAll();

    Optional<MonitoredEndpointData> findById(int id);

    boolean existsByNameAndOwnerId(String name, int ownerId);
}
