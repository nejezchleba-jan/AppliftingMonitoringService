package cz.jannejezchleba.monitoringservice.util;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.GetAllEndpointsUseCase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


// Relaunches all monitoring for endpoints on server startup
@Component
public class MonitorStartupRunner implements ApplicationRunner {

    private final GetAllEndpointsUseCase getAllEndpointsUseCase;
    private final Scheduling scheduling;

    public MonitorStartupRunner(GetAllEndpointsUseCase getAllEndpointsUseCase,
                                Scheduling scheduling) {
        this.getAllEndpointsUseCase = getAllEndpointsUseCase;
        this.scheduling = scheduling;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<MonitoredEndpoint> endpoints = getAllEndpointsUseCase.execute(new GetAllEndpointsUseCase.InputValues()).getAllEndpoints();
        endpoints.forEach(scheduling::addTaskToScheduling);
    }
}