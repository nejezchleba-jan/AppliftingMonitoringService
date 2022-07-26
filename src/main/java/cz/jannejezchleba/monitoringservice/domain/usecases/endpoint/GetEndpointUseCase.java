package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.exceptions.NotFoundException;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import lombok.Value;

import java.util.Optional;

public class GetEndpointUseCase extends UseCase<GetEndpointUseCase.InputValues, GetEndpointUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;


    public GetEndpointUseCase(MonitoredEndpointRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {

        Optional<MonitoredEndpoint> endpoint = repository.getById(input.endpointId);
        if (endpoint.isEmpty() || endpoint.get().getOwner().getId() != input.userId) {
            throw new NotFoundException("Endpoint %s not found", input.endpointId);
        }

        return new OutputValues(endpoint.get());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int endpointId;
        int userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        MonitoredEndpoint endpoint;
    }
}
