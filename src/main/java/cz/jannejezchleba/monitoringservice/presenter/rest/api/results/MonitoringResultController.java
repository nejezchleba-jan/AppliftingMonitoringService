package cz.jannejezchleba.monitoringservice.presenter.rest.api.results;

import cz.jannejezchleba.monitoringservice.domain.usecases.UseCaseExecutor;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.GetTenRecentMonitoringResultsUseCase;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.MonitoringResultResponse;
import cz.jannejezchleba.monitoringservice.presenter.rest.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class MonitoringResultController implements MonitoringResultResource {
    private final UseCaseExecutor useCaseExecutor;
    private final GetTenRecentMonitoringResultsUseCase getTenRecentMonitoringResultsUseCase;

    public MonitoringResultController(@Qualifier("useCaseExecutorImpl") UseCaseExecutor useCaseExecutor,
                                        GetTenRecentMonitoringResultsUseCase getTenRecentMonitoringResultsUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getTenRecentMonitoringResultsUseCase = getTenRecentMonitoringResultsUseCase;
    }

    @Override
    public CompletableFuture<List<MonitoringResultResponse>> getLastTenForUser(String id, UserPrincipal userPrincipal) {
        return useCaseExecutor.execute(
                getTenRecentMonitoringResultsUseCase,
                new GetTenRecentMonitoringResultsUseCase.InputValues(Integer.parseInt(id), userPrincipal.getId()),
                (outputValues) -> MonitoringResultResponse.from(outputValues.getResultList()));
    }
}
