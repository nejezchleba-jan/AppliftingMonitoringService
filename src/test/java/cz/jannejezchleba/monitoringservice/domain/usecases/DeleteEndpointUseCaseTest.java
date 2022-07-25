package cz.jannejezchleba.monitoringservice.domain.usecases;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.DeleteEndpointUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.MonitoringResultRepository;
import cz.jannejezchleba.monitoringservice.util.Scheduling;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class DeleteEndpointUseCaseTest {

    @Mock
    private MonitoredEndpointRepository repository;

    @Mock
    private Scheduling scheduling;

    @Mock
    private MonitoringResultRepository monitoringResultRepository;

    @InjectMocks
    private DeleteEndpointUseCase useCase;

    @Test
    public void executeReturnsEndpointIdWhenEndpointIsDeleted() {
        MonitoredEndpoint original = TestEntityGenerator.generateEndpoint();

        // given
        DeleteEndpointUseCase.InputValues input = new DeleteEndpointUseCase.InputValues(
                original.getId(),
                original.getOwner().getId());


        // and
        doReturn(Optional.of(original))
                .when(repository)
                .getById(eq(input.getId()));

        // and
        doReturn(original.getId())
                .when(repository)
                .deleteById(original.getId());

        // when
        int actual = useCase.execute(input).getDeletedId();

        // then
        assertThat(actual).isEqualTo(original.getId());
    }
}
