package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.exceptions.NotFoundException;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.MonitoringResultRepository;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import lombok.Value;

import javax.transaction.Transactional;
import java.util.Optional;

public class DeleteEndpointUseCase extends UseCase<DeleteEndpointUseCase.InputValues, DeleteEndpointUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;
    private final MonitoringResultRepository monitoringResultRepository;
    private final Scheduling scheduling;

    public DeleteEndpointUseCase(MonitoredEndpointRepository repository, MonitoringResultRepository monitoringResultRepository, Scheduling scheduling) {
        this.repository = repository;
        this.monitoringResultRepository = monitoringResultRepository;
        this.scheduling = scheduling;
    }

    @Override
    @Transactional
    public OutputValues execute(InputValues input) {
        final int id = input.id;
        Optional<MonitoredEndpoint> endpoint = repository.getById(id);

        if (endpoint.isEmpty() || endpoint.get().getOwner().getId() != input.userId) {
            throw new NotFoundException("Endpoint %s not found", id);
        }

        this.monitoringResultRepository.deleteAllByEndpointId(endpoint.get().getId());

        OutputValues outputValues = new OutputValues(this.repository.deleteById(id));

        scheduling.deleteTaskFromScheduling(outputValues.deletedId);

        return outputValues;
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int id;
        int userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        int deletedId;
    }
}
