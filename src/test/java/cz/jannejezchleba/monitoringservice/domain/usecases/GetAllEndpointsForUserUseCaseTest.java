package cz.jannejezchleba.monitoringservice.domain.usecases;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.GetAllEndpointsForUserUseCase;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GetAllEndpointsForUserUseCaseTest {

    @Mock
    private MonitoredEndpointRepository repository;

    @InjectMocks
    private GetAllEndpointsForUserUseCase useCase;

    @Test
    public void executeReturnsAllEndpointsForUser() {
        User owner = TestEntityGenerator.generateUser();
        MonitoredEndpoint endpoint1 = TestEntityGenerator.generateEndpointWithOwner(owner);
        MonitoredEndpoint endpoint2 = TestEntityGenerator.generateEndpointWithOwner(owner);

        // given
        GetAllEndpointsForUserUseCase.InputValues input = new GetAllEndpointsForUserUseCase.InputValues(owner.getId());

        // and
        doReturn(List.of(endpoint1, endpoint2))
                .when(repository)
                .getAllForUser(eq(input.getUserId()));

        // when
        List<MonitoredEndpoint> actual = useCase.execute(input).getEndpointList();

        // then
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.contains(endpoint1)).isTrue();
        assertThat(actual.contains(endpoint2)).isTrue();
    }
}
