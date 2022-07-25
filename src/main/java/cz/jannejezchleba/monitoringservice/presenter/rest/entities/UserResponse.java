package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import lombok.Value;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class UserResponse {
    int id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String accessToken;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAccessToken()
                );
    }

    public static List<UserResponse> from(List<User> users) {
        return users
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}