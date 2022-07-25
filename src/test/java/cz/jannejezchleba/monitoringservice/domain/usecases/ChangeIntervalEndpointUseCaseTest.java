package cz.jannejezchleba.monitoringservice.domain.usecases;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.ChangeIntervalEndpointUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ChangeIntervalEndpointUseCaseTest {

    @Mock
    private MonitoredEndpointRepository repository;

    @Mock
    private Scheduling scheduling;

    @InjectMocks
    private ChangeIntervalEndpointUseCase useCase;

    @Test
    public void executeReturnsChangedEndpointWhenIntervalIsChanged() {
        int newInterval= 70;
        MonitoredEndpoint original = TestEntityGenerator.generateEndpoint();
        MonitoredEndpoint expected = original.changeMonitorInterval(newInterval);

        // given
        ChangeIntervalEndpointUseCase.InputValues input = new ChangeIntervalEndpointUseCase.InputValues(
                expected.getId(),
                newInterval,
                expected.getOwner().getId());


        // and
        doReturn(Optional.of(original))
                .when(repository)
                .getById(eq(input.getId()));

        // and
        doReturn(expected)
                .when(repository)
                .persist(any(MonitoredEndpoint.class));

        // when
        MonitoredEndpoint actual = useCase.execute(input).getEndpoint();

        // then
        assertThat(actual.getMonitoredInterval()).isEqualTo(expected.getMonitoredInterval());
    }
}
