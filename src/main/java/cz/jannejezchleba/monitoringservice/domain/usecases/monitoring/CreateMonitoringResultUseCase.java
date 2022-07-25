package cz.jannejezchleba.monitoringservice.domain.usecases.monitoring;


import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import lombok.Value;

import java.time.Instant;

public class CreateMonitoringResultUseCase extends UseCase<CreateMonitoringResultUseCase.InputValues,CreateMonitoringResultUseCase.OutputValues> {
    private final MonitoringResultRepository repository;

    public CreateMonitoringResultUseCase(MonitoringResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        //TODO check if endpoint exists?
        MonitoringResult result = MonitoringResult
                .builder()
                .checkedAt(Instant.now())
                .httpStatusCode(input.httpStatusCode)
                .returnedPayload(input.returnedPayload)
                .monitoredEndpoint(input.monitoredEndpoint)
                .build();

        return new OutputValues(repository.persist(result));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
         int httpStatusCode;
         String returnedPayload;
         MonitoredEndpoint monitoredEndpoint;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        MonitoringResult result;
    }
}


