package cz.jannejezchleba.monitoringservice.presenter.rest.api.endpoints;

import cz.jannejezchleba.monitoringservice.domain.usecases.UseCaseExecutor;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.*;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.*;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Controller
public class EndpointManagementController implements EndpointManagementResource {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateEndpointUseCase createEndpointUseCase;
    private final GetAllEndpointsForUserUseCase getAllEndpointsForUserUseCase;
    private final DeleteEndpointUseCase deleteEndpointUseCase;
    private final RenameEndpointUseCase renameEndpointUseCase;
    private final ChangeIntervalEndpointUseCase changeIntervalEndpointUseCase;

    public EndpointManagementController(@Qualifier("useCaseExecutorImpl") UseCaseExecutor useCaseExecutor,
                                        CreateEndpointUseCase createEndpointUseCase,
                                        GetAllEndpointsForUserUseCase getAllEndpointsForUserUseCase,
                                        DeleteEndpointUseCase deleteEndpointUseCase,
                                        RenameEndpointUseCase renameEndpointUseCase,
                                        ChangeIntervalEndpointUseCase changeIntervalEndpointUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createEndpointUseCase = createEndpointUseCase;
        this.getAllEndpointsForUserUseCase = getAllEndpointsForUserUseCase;
        this.deleteEndpointUseCase = deleteEndpointUseCase;
        this.renameEndpointUseCase = renameEndpointUseCase;
        this.changeIntervalEndpointUseCase = changeIntervalEndpointUseCase;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> create(UserPrincipal userPrincipal,
                                                                 HttpServletRequest httpServletRequest,
                                                                 MonitoredEndpointRequest endpointRequest) {
        return useCaseExecutor.execute(
                createEndpointUseCase,
                CreateEndpointInputMapper.map(endpointRequest, userPrincipal),
                (outputValues) -> CreateEndpointOutputMapper.map(outputValues.getEndpoint(), httpServletRequest));


    }

    @Override
    public CompletableFuture<List<MonitoredEndpointResponse>> getAllForUser(UserPrincipal userPrincipal) {
        return useCaseExecutor.execute(
                getAllEndpointsForUserUseCase,
                new GetAllEndpointsForUserUseCase.InputValues(userPrincipal.getId()),
                (outputValues) -> MonitoredEndpointResponse.from(outputValues.getEndpointList()));
    }

    @Override
    public CompletableFuture<ApiResponse> renameEndpoint(int id, RenameRequest request, UserPrincipal userPrincipal) {
        return useCaseExecutor.execute(
                renameEndpointUseCase,
                new RenameEndpointUseCase.InputValues(id, request.getName(), userPrincipal.getId()),
                (outputValues) -> new ApiResponse(true, "Endpoint name successfully changed"));
    }

    @Override
    public CompletableFuture<ApiResponse> changeMonitorInterval(int id, ChangeIntervalRequest request, UserPrincipal userPrincipal) {
        return useCaseExecutor.execute(
                changeIntervalEndpointUseCase,
                new ChangeIntervalEndpointUseCase.InputValues(id, request.getMonitoredInterval(), userPrincipal.getId()),
                (outputValues) -> new ApiResponse(true, "Endpoint interval successfully changed"));
    }

    @Override
    public CompletableFuture<ApiResponse> deleteEndpoint(int id, UserPrincipal userPrincipal) {
        return useCaseExecutor.execute(
                deleteEndpointUseCase,
                new DeleteEndpointUseCase.InputValues(id, userPrincipal.getId()),
                (outputValues) -> new ApiResponse(true, "Endpoint successfully deleted"));
    }
}
