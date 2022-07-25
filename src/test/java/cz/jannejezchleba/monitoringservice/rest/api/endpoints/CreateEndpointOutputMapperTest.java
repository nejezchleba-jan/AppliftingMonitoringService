package cz.jannejezchleba.monitoringservice.rest.api.endpoints;

import cz.jannejezchleba.monitoringservice.TestEntityGenerator;
import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.presenter.rest.api.endpoints.CreateEndpointOutputMapper;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateEndpointOutputMapperTest {

    @Test
    public void mapEndpointToResponseCreated() {
        // given
        MonitoredEndpoint endpoint = TestEntityGenerator.generateEndpoint();
        HttpServletRequest httpServletRequest = new MockHttpServletRequest("", "");

        // when
        ResponseEntity<ApiResponse> actual = CreateEndpointOutputMapper.map(endpoint, httpServletRequest);

        // then
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(actual.getBody().getSuccess()).isTrue();
        assertThat(actual.getBody().getMessage()).isEqualTo(String.format("Monitored endpoint with id=%s for %s created successfully",endpoint.getId(), endpoint.getUrl()));
        assertThat(actual.getHeaders().getLocation().toString()).isEqualTo("http://localhost/endpoints/" + endpoint.getId());
    }
}
