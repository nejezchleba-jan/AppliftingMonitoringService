package cz.jannejezchleba.monitoringservice.presenter.rest.api.users;

import cz.jannejezchleba.monitoringservice.presenter.rest.entities.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Validated
@RequestMapping("/users")
public interface UserResource {

    @GetMapping("/all")
    CompletableFuture<List<UserResponse>> getAllUsers();
}
