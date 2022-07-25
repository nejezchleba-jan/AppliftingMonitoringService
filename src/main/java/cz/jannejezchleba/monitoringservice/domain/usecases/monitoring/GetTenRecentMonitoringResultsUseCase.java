package cz.jannejezchleba.monitoringservice.domain.usecases.monitoring;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;
import cz.jannejezchleba.monitoringservice.domain.exceptions.NotFoundException;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import lombok.Value;

import java.util.List;
import java.util.Optional;

public class GetTenRecentMonitoringResultsUseCase extends UseCase<GetTenRecentMonitoringResultsUseCase.InputValues, GetTenRecentMonitoringResultsUseCase.OutputValues> {
    private final MonitoringResultRepository repository;
    private final MonitoredEndpointRepository endpointRepository;

    public GetTenRecentMonitoringResultsUseCase(MonitoringResultRepository repository,
                                                MonitoredEndpointRepository endpointRepository) {
        this.repository = repository;
        this.endpointRepository = endpointRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {

        Optional<MonitoredEndpoint> endpointToChange = endpointRepository.getById(input.endpointId);

        if (endpointToChange.isEmpty() || endpointToChange.get().getOwner().getId() != input.userId) {
            throw new NotFoundException("Endpoint %s not found", input.endpointId);
        }

        return new OutputValues(repository.getTenLastResultsForEndpoint(input.endpointId, input.userId));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        int endpointId;
        int userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<MonitoringResult> resultList;
    }
}