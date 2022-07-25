package cz.jannejezchleba.monitoringservice.domain.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final int id;
    private final String username;
    private final String email;
    private final String accessToken;

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}
