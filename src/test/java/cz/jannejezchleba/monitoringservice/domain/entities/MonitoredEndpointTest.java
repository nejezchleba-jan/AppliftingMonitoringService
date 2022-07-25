package cz.jannejezchleba.monitoringservice.domain.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class MonitoredEndpointTest {

    @Test
    public void testBuilder() {
        // given
        int id = 10 ;
        String name = "test";
        String url = "http://test.com";
        int monitoredInterval = 50;
        User owner = User.builder().id(1).username("Tester").accessToken("").email("email@test.com").build();
        Instant createdAt = Instant.now();


        // when
        MonitoredEndpoint actual = MonitoredEndpoint
                .builder()
                .id(id)
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .monitoredInterval(monitoredInterval)
                .owner(owner)
                .build();

        // then
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getUrl()).isEqualTo(url);
        assertThat(actual.getMonitoredInterval()).isEqualTo(monitoredInterval);
        assertThat(actual.getCreatedAt()).isEqualTo(createdAt);
        assertThat(actual.getOwner().getId()).isEqualTo(owner.getId());
        assertThat(actual.getLastCheckAt()).isNull();
    }
}
