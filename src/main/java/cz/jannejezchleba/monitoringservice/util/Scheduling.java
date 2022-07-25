package cz.jannejezchleba.monitoringservice.util;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;
import cz.jannejezchleba.monitoringservice.domain.usecases.endpoint.MonitoredEndpointRepository;
import cz.jannejezchleba.monitoringservice.domain.usecases.monitoring.MonitoringResultRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class Scheduling {

    private final MonitoringResultRepository monitoringResultRepository;
    private final MonitoredEndpointRepository monitoredEndpointRepository;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final RestTemplate restTemplate;
    private final ConcurrentHashMap<Integer, ScheduledFuture> scheduledTasksMap = new ConcurrentHashMap();

    public Scheduling(MonitoringResultRepository monitoringResultRepository,
                      MonitoredEndpointRepository monitoredEndpointRepository,
                      ThreadPoolTaskScheduler taskScheduler,
                      RestTemplate restTemplate) {
        this.monitoringResultRepository = monitoringResultRepository;
        this.monitoredEndpointRepository = monitoredEndpointRepository;
        this.taskScheduler = taskScheduler;
        this.restTemplate = restTemplate;
    }

    public void addTaskToScheduling(MonitoredEndpoint endpoint) {
        ScheduledFuture future = taskScheduler.scheduleAtFixedRate(new MonitorEndpointTask(endpoint), endpoint.getMonitoredInterval() * 1000L);
        scheduledTasksMap.put(endpoint.getId(), future);
    }

    public void updateScheduledTask(MonitoredEndpoint endpoint) {
        deleteTaskFromScheduling(endpoint.getId());
        addTaskToScheduling(endpoint);
    }

    public void deleteTaskFromScheduling(int endpointId) {
        ScheduledFuture task = scheduledTasksMap.get(endpointId);
        task.cancel(true);
        scheduledTasksMap.remove(endpointId);
    }

    public class MonitorEndpointTask implements Runnable {
        private MonitoredEndpoint endpoint;

        public MonitorEndpointTask(MonitoredEndpoint endpoint) {
            this.endpoint = endpoint;
        }

        @Override
        public void run() {
            ResponseEntity<String> response = restTemplate.exchange(endpoint.getUrl(), HttpMethod.GET, null, String.class);
            Instant lastCheck = Instant.now();
            MonitoringResult monitoringResult = MonitoringResult
                    .builder()
                    .monitoredEndpoint(endpoint)
                    .httpStatusCode(response.getStatusCodeValue())
                    .returnedPayload(response.getBody())
                    .checkedAt(lastCheck)
                    .build();

            endpoint = MonitoredEndpoint
                    .builder()
                    .id(endpoint.getId())
                    .name(endpoint.getName())
                    .url(endpoint.getUrl())
                    .monitoredInterval(endpoint.getMonitoredInterval())
                    .createdAt(endpoint.getCreatedAt())
                    .owner(endpoint.getOwner())
                    .lastCheckAt(lastCheck)
                    .build();

            monitoringResultRepository.persist(monitoringResult);
            monitoredEndpointRepository.persist(endpoint);
        }
    }
}
