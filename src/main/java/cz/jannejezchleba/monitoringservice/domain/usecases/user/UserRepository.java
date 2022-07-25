package cz.jannejezchleba.monitoringservice.domain.usecases.user;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByToken(String token);
    Optional<User> getUserByEmail(String email);
    List<User> findAllUsers();
}
