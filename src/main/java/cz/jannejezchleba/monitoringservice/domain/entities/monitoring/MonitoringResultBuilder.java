package cz.jannejezchleba.monitoringservice.domain.entities.monitoring;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;

import java.time.Instant;

public class MonitoringResultBuilder {
    private int id;
    private Instant checkedAt;
    private int httpStatusCode;
    private String returnedPayload;
    private MonitoredEndpoint monitoredEndpoint;

    public MonitoringResultBuilder id(int id) {
        this.id = id;
        return this;
    }

    public MonitoringResultBuilder checkedAt(Instant checkedAt) {
        this.checkedAt = checkedAt;
        return this;
    }

    public MonitoringResultBuilder returnedPayload(String returnedPayload) {
        this.returnedPayload = returnedPayload;
        return this;
    }

    public MonitoringResultBuilder httpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public MonitoringResultBuilder monitoredEndpoint(MonitoredEndpoint monitoredEndpoint) {
        this.monitoredEndpoint = monitoredEndpoint;
        return this;
    }

    public MonitoringResult build() {
        return new MonitoringResult(this.id, this.checkedAt, this.httpStatusCode, this.returnedPayload, this.monitoredEndpoint);
    }
}
