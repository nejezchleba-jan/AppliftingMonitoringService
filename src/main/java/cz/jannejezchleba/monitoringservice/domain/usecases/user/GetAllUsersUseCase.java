package cz.jannejezchleba.monitoringservice.domain.usecases.user;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import cz.jannejezchleba.monitoringservice.domain.usecases.UseCase;
import lombok.Value;

import java.util.List;

public class GetAllUsersUseCase extends UseCase<GetAllUsersUseCase.InputValues, GetAllUsersUseCase.OutputValues> {
    private final UserRepository repository;

    public GetAllUsersUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.findAllUsers());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<User> userList;
    }
}
