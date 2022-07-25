package cz.jannejezchleba.monitoringservice.presenter.rest.api.endpoints;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.CreateEndpointUseCase;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.MonitoredEndpointRequest;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

public final class CreateEndpointInputMapper {

    public static CreateEndpointUseCase.InputValues map(MonitoredEndpointRequest endpointRequest, UserDetails userDetails) {
        return new CreateEndpointUseCase.InputValues(
                endpointRequest.getName(),
                endpointRequest.getUrl(),
                endpointRequest.getMonitoredInterval(),
                map(userDetails)
        );
    }

    public static User map(UserDetails userDetails) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;

        return User.builder()
                .id(userPrincipal.getId())
                .username(userPrincipal.getUsername())
                .email(userPrincipal.getEmail())
                .accessToken(userPrincipal.getAccessToken())
                .build();
    }
}