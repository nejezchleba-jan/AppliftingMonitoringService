package cz.jannejezchleba.monitoringservice.presenter.rest.api.results;

import cz.jannejezchleba.monitoringservice.presenter.rest.entities.MonitoringResultResponse;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.CurrentUser;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Validated
@RequestMapping("/monitoring")
public interface MonitoringResultResource {

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<List<MonitoringResultResponse>> getLastTenForUser(@PathVariable(name = "id") String id, @CurrentUser UserPrincipal userPrincipal);
}
