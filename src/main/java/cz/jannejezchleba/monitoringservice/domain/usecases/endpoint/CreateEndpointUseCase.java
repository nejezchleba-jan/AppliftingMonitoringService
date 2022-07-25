package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import lombok.Value;

import java.time.Instant;

public class CreateEndpointUseCase extends UseCase<CreateEndpointUseCase.InputValues, CreateEndpointUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;
    private final Scheduling scheduling;

    public CreateEndpointUseCase(MonitoredEndpointRepository repository, Scheduling scheduling) {
        this.repository = repository;
        this.scheduling = scheduling;
    }

    @Override
    public OutputValues execute(InputValues input) {

        MonitoredEndpoint endpoint = MonitoredEndpoint
                .builder()
                .name(input.name)
                .url(input.url)
                .createdAt(Instant.now())
                .monitoredInterval(input.monitoredInterval)
                .owner(input.owner)
                .build();

        OutputValues outputValues = new OutputValues(repository.persist(endpoint));

        //create scheduled monitoring task
        scheduling.addTaskToScheduling(outputValues.getEndpoint());
        return outputValues;

    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String name;
        String url;
        int monitoredInterval;
        User owner;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        MonitoredEndpoint endpoint;
    }
}
