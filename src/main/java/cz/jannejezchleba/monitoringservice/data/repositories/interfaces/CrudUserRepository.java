package cz.jannejezchleba.monitoringservice.data.repositories.interfaces;

import cz.jannejezchleba.monitoringservice.data.entities.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudUserRepository extends CrudRepository<UserData, Integer> {
    Optional<UserData> getByAccessToken(String accessToken);
    Optional<UserData> getByEmail(String email);
    List<UserData> findAll();
}
