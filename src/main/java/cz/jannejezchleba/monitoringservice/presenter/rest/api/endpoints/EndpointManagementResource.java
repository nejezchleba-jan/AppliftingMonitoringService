package cz.jannejezchleba.monitoringservice.presenter.rest.api.endpoints;

import cz.jannejezchleba.monitoringservice.presenter.rest.entities.*;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.CurrentUser;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@Validated
@RequestMapping("/endpoints")
public interface EndpointManagementResource {

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ResponseEntity<ApiResponse>> create(@CurrentUser UserPrincipal userPrincipal,
                                                          HttpServletRequest httpServletRequest,
                                                          @Valid @RequestBody MonitoredEndpointRequest endpointRequest);

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<List<MonitoredEndpointResponse>> getAllForUser(@CurrentUser UserPrincipal userPrincipal);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<MonitoredEndpointResponse> getEndpoint(@PathVariable(name = "id") int id, @CurrentUser UserPrincipal userPrincipal);

    @PutMapping("/rename/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ApiResponse> renameEndpoint(@PathVariable(name = "id") int id,
                                                  @Valid @RequestBody RenameRequest renameRequest,
                                                  @CurrentUser UserPrincipal userPrincipal);

    @PutMapping("/change-interval/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ApiResponse> changeMonitorInterval(@PathVariable(name = "id") int id,
                                                         @Valid @RequestBody ChangeIntervalRequest changeIntervalRequest,
                                                         @CurrentUser UserPrincipal userPrincipal);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ApiResponse> deleteEndpoint(@PathVariable(name = "id") int id, @CurrentUser UserPrincipal userPrincipal);
}
