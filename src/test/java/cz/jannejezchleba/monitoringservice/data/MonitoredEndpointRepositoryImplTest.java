package cz.jannejezchleba.monitoringservice.data;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.data.entities.MonitoredEndpointData;
import cz.jannejezchleba.monitoringservice.data.repositories.MonitoredEndpointRepositoryImpl;
import cz.jannejezchleba.monitoringservice.data.repositories.interfaces.CrudEndpointRepository;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MonitoredEndpointRepositoryImplTest {
    @Mock
    private CrudEndpointRepository crudRepository;

    @InjectMocks
    private MonitoredEndpointRepositoryImpl repository;

    @Test
    public void getByIdReturnsEndpoint() {
        // given
        MonitoredEndpoint expected = TestEntityGenerator.generateEndpoint();
        MonitoredEndpointData toBeReturned = MonitoredEndpointData.from(expected);

        // and
        doReturn(Optional.of(toBeReturned))
                .when(crudRepository)
                .findById(eq(expected.getId()));

        // when
        Optional<MonitoredEndpoint> actual = repository.getById(expected.getId());

        // then
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getId()).isEqualTo(expected.getId());
    }

    @Test
    public void deleteByIdReturnsDeletedId() {
        // given
        int expected = TestEntityGenerator.generateEndpoint().getId();

        // and
        doNothing()
            .when(crudRepository)
            .deleteById(eq(expected));

        // when
        int actual = repository.deleteById(expected);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void persistReturnsSavedEndpoint() {
        // given
        MonitoredEndpoint original = TestEntityGenerator.generateEndpoint();
        MonitoredEndpointData tobeSaved = MonitoredEndpointData.from(original);

        // and
        doReturn(tobeSaved)
                .when(crudRepository)
                .save(tobeSaved);

        // when
        MonitoredEndpoint actual = repository.persist(original);

        // then
        assertThat(actual.getId()).isEqualTo(tobeSaved.getId());
        assertThat(actual.getName()).isEqualTo(tobeSaved.getName());
        assertThat(actual.getUrl()).isEqualTo(tobeSaved.getUrl());
        assertThat(actual.getMonitoredInterval()).isEqualTo(tobeSaved.getMonitoredInterval());
        assertThat(actual.getCreatedAt()).isEqualTo(tobeSaved.getCreatedAt());
        assertThat(actual.getOwner().getId()).isEqualTo(tobeSaved.getOwner().getId());
        assertThat(actual.getLastCheckAt()).isEqualTo(tobeSaved.getLastCheckAt());
    }
}