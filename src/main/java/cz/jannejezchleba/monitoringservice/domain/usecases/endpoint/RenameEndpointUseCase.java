package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.exceptions.NotFoundException;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import lombok.Value;

import java.util.Optional;

public class RenameEndpointUseCase extends UseCase<RenameEndpointUseCase.InputValues, RenameEndpointUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;


    public RenameEndpointUseCase(MonitoredEndpointRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        final int id = input.id;

        Optional<MonitoredEndpoint> endpointToChange = repository.getById(id);
        if (endpointToChange.isEmpty() || endpointToChange.get().getOwner().getId() != input.userId) {
            throw new NotFoundException("Endpoint %s not found", id);
        }

        MonitoredEndpoint changedEndpoint = endpointToChange.get().rename(input.name);

        return new OutputValues(this.repository.persist(changedEndpoint));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int id;
        String name;
        int userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        MonitoredEndpoint endpoint;
    }
}