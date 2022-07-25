package cz.jannejezchleba.monitoringservice.domain.usecases;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.RenameEndpointUseCase;
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
public class RenameEndpointUseCaseTest {

    @Mock
    private MonitoredEndpointRepository repository;

    @InjectMocks
    private RenameEndpointUseCase useCase;

    @Test
    public void executeReturnsRenamedEndpointWhenEndpointIsRenamed() {
        String newName = "newName";
        MonitoredEndpoint original = TestEntityGenerator.generateEndpoint();
        MonitoredEndpoint expected = original.rename(newName);

        // given
        RenameEndpointUseCase.InputValues input = new RenameEndpointUseCase.InputValues(
                expected.getId(),
                newName,
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
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }
}
