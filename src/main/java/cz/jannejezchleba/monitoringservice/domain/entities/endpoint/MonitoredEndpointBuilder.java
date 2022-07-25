package cz.jannejezchleba.monitoringservice.domain.entities.endpoint;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;

import java.time.Instant;

public final class MonitoredEndpointBuilder {
    private int id;
    private String name;
    private String url;
    private Instant createdAt;
    private Instant lastCheckAt;
    private int monitoredInterval;
    private User owner;

    MonitoredEndpointBuilder() { }

    public MonitoredEndpointBuilder id(int id) {
        this.id = id;
        return this;
    }

    public MonitoredEndpointBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MonitoredEndpointBuilder url(String url) {
        this.url = url;
        return this;
    }

    public MonitoredEndpointBuilder createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public MonitoredEndpointBuilder lastCheckAt(Instant lastCheckAt) {
        this.lastCheckAt = lastCheckAt;
        return this;
    }

    public MonitoredEndpointBuilder monitoredInterval(int monitoredInterval) {
        this.monitoredInterval = monitoredInterval;
        return this;
    }

    public MonitoredEndpointBuilder owner(User owner) {
        this.owner = owner;
        return this;
    }

    public MonitoredEndpoint build() {
        return new MonitoredEndpoint(this.id, this.name, this.url, this.createdAt, this.lastCheckAt, this.monitoredInterval, this.owner);
    }
}
