package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.exceptions.InvalidArgumentException;
import cz.jannejezchleba.monitoringservice.domain.exceptions.NotFoundException;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import lombok.Value;

import java.util.Optional;

public class ChangeIntervalEndpointUseCase extends UseCase<ChangeIntervalEndpointUseCase.InputValues, ChangeIntervalEndpointUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;
    private final Scheduling scheduling;

    public ChangeIntervalEndpointUseCase(MonitoredEndpointRepository repository, Scheduling scheduling) {
        this.repository = repository;
        this.scheduling = scheduling;
    }

    @Override
    public OutputValues execute(InputValues input) {
        final int id = input.id;

        Optional<MonitoredEndpoint> endpointToChange = repository.getById(id);
        if (endpointToChange.isEmpty() || endpointToChange.get().getOwner().getId() != input.userId) {
            throw new NotFoundException("Endpoint %s not found", id);
        } else if (input.interval <= 5) {
            throw new InvalidArgumentException("Interval %s is not valid, minimal value is 5 seconds.", input.interval);
        }

        MonitoredEndpoint changedEndpoint = endpointToChange.get().changeMonitorInterval(input.interval);

        OutputValues outputValues = new OutputValues(this.repository.persist(changedEndpoint));
        
        scheduling.updateScheduledTask(outputValues.getEndpoint());

        return outputValues;
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int id;
        int interval;
        int userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        MonitoredEndpoint endpoint;
    }
}


