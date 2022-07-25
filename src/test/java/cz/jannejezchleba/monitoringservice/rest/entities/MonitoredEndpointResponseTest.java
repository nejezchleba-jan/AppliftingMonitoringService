package cz.jannejezchleba.monitoringservice.rest.entities;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.MonitoredEndpointResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MonitoredEndpointResponseTest {
    @Test
    public void fromMonitoredEndpoint() {
        // given
        MonitoredEndpoint endpoint = TestEntityGenerator.generateEndpoint();

        // when
        MonitoredEndpointResponse actual = MonitoredEndpointResponse.from(endpoint);

        // then
        assertThat(actual.getId()).isEqualTo(endpoint.getId());
        assertThat(actual.getName()).isEqualTo(endpoint.getName());
        assertThat(actual.getUrl()).isEqualTo(endpoint.getUrl());
        assertThat(actual.getMonitoredInterval()).isEqualTo(endpoint.getMonitoredInterval());
        assertThat(actual.getCreatedAt()).isEqualTo(endpoint.getCreatedAt());
        assertThat(actual.getOwnerId()).isEqualTo(endpoint.getOwner().getId());
        assertThat(actual.getLastCheckAt()).isNull();
    }
}
