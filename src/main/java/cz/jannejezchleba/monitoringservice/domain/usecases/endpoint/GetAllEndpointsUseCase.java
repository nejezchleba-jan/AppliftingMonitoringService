package cz.jannejezchleba.monitoringservice.domain.usecases.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import lombok.Value;

import java.util.List;

public class GetAllEndpointsUseCase extends UseCase<GetAllEndpointsUseCase.InputValues, GetAllEndpointsUseCase.OutputValues> {
    private final MonitoredEndpointRepository repository;

    public GetAllEndpointsUseCase(MonitoredEndpointRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(GetAllEndpointsUseCase.InputValues input) {
        return new OutputValues(repository.getAll());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<MonitoredEndpoint> allEndpoints;
    }

}
