package cz.jannejezchleba.monitoringservice.presenter.config;

import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.*;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.CreateMonitoringResultUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.GetTenRecentMonitoringResultsUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.MonitoringResultRepository;
import cz.jannejezchleba.monitoringservice.domain.usecases.user.GetAllUsersUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.user.UserRepository;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseModule {

    //Users

    @Bean
    public GetAllUsersUseCase getAllUsersUseCase(UserRepository userRepository) {
        return new GetAllUsersUseCase(userRepository);
    }


    //Monitored endpoints

    @Bean
    public CreateEndpointUseCase createEndpointUseCase(MonitoredEndpointRepository repository,
                                                       Scheduling scheduling) {
        return new CreateEndpointUseCase(repository, scheduling);
    }

    @Bean
    public DeleteEndpointUseCase deleteEndpointUseCase(MonitoredEndpointRepository repository,
                                                       Scheduling scheduling,
                                                       MonitoringResultRepository monitoringResultRepository) {
        return new DeleteEndpointUseCase(repository, monitoringResultRepository, scheduling);
    }

    @Bean
    public RenameEndpointUseCase renameEndpointUseCase(MonitoredEndpointRepository repository) {
        return new RenameEndpointUseCase(repository);
    }

    @Bean
    public ChangeIntervalEndpointUseCase changeIntervalEndpointUseCase(MonitoredEndpointRepository repository,
                                                                       Scheduling scheduling) {
        return new ChangeIntervalEndpointUseCase(repository, scheduling);
    }

    @Bean
    public GetAllEndpointsForUserUseCase getAllForUserEndpointUseCase(MonitoredEndpointRepository repository) {
        return new GetAllEndpointsForUserUseCase(repository);
    }

    @Bean
    public GetAllEndpointsUseCase getAllEndpointsUseCase(MonitoredEndpointRepository repository) {
        return new GetAllEndpointsUseCase(repository);
    }

    // Monitoring results

    @Bean
    public GetTenRecentMonitoringResultsUseCase getTenRecentMonitoringResultsUseCase(MonitoringResultRepository repository,
                                                                                     MonitoredEndpointRepository endpointRepository) {
        return new GetTenRecentMonitoringResultsUseCase(repository, endpointRepository);
    }

    @Bean
    public CreateMonitoringResultUseCase createMonitoringResultUseCase(MonitoringResultRepository repository) {
        return new CreateMonitoringResultUseCase(repository);
    }
}
