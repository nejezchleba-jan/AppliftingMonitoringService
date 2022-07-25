package cz.jannejezchleba.monitoringservice.presenter.rest.api.users;

import cz.jannejezchleba.monitoringservice.domain.usecases.UseCaseExecutor;
import cz.jannejezchleba.monitoringservice.domain.usecases.user.GetAllUsersUseCase;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.UserResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class UserController implements UserResource {

    private final UseCaseExecutor useCaseExecutor;
    private final GetAllUsersUseCase getAllUsersUseCase;

    public UserController(@Qualifier("useCaseExecutorImpl") UseCaseExecutor useCaseExecutor,
                          GetAllUsersUseCase getAllUsersUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getAllUsersUseCase = getAllUsersUseCase;
    }

    @Override
    public CompletableFuture<List<UserResponse>> getAllUsers() {
        return useCaseExecutor.execute(
                getAllUsersUseCase,
                new GetAllUsersUseCase.InputValues(),
                (outputValues) -> UserResponse.from(outputValues.getUserList()));
    }
}
