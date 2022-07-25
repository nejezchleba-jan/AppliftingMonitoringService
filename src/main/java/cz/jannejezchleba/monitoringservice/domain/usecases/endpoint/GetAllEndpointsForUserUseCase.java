package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import lombok.Value;

import java.util.List;

public class GetAllEndpointsForUserUseCase extends UseCase<GetAllEndpointsForUserUseCase.InputValues, GetAllEndpointsForUserUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;

    public GetAllEndpointsForUserUseCase(MonitoredEndpointRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAllForUser(input.userId));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
       List<MonitoredEndpoint> endpointList;
    }
}
