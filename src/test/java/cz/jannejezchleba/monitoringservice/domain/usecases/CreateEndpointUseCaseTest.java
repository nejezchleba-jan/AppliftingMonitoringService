package cz.jannejezchleba.monitoringservice.domain.usecases;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.CreateEndpointUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CreateEndpointUseCaseTest {

    @Mock
    private MonitoredEndpointRepository repository;

    @Mock
    private Scheduling scheduling;

    @InjectMocks
    private CreateEndpointUseCase useCase;

    @Test
    public void executeReturnsEndpointWhenEndpointIsCreated() {
        MonitoredEndpoint expected = TestEntityGenerator.generateEndpoint();

        // given
        CreateEndpointUseCase.InputValues input = new CreateEndpointUseCase.InputValues(
                expected.getName(),
                expected.getUrl(),
                expected.getMonitoredInterval(),
                expected.getOwner());


        // and
        doReturn(expected)
                .when(repository)
                .persist(any(MonitoredEndpoint.class));

        // when
        MonitoredEndpoint actual = useCase.execute(input).getEndpoint();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
