package cz.jannejezchleba.monitoringservice.data.repositories;

import cz.jannejezchleba.monitoringservice.data.entities.UserData;
import cz.jannejezchleba.monitoringservice.data.repositories.interfaces.CrudUserRepository;
import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import cz.jannejezchleba.monitoringservice.domain.usecases.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final CrudUserRepository repository;

    public UserRepositoryImpl(CrudUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getUserByToken(String token) {
        return repository
                .getByAccessToken(token)
                .map(UserData::toDomain);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return repository
                .getByEmail(email)
                .map(UserData::toDomain);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserData::toDomain)
                .collect(Collectors.toList());
    }
}
