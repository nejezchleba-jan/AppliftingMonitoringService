package cz.jannejezchleba.monitoringservice.presenter.rest.api.endpoints;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public final class CreateEndpointOutputMapper {

    public static ResponseEntity<ApiResponse> map(MonitoredEndpoint endpoint, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/endpoints/{id}")
                .buildAndExpand(endpoint.getId())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, String.format("Monitored endpoint with id=%s for %s created successfully",endpoint.getId(), endpoint.getUrl())));
    }
}
