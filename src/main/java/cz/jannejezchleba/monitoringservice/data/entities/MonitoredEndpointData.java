package cz.jannejezchleba.monitoringservice.data.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.endpoint.MonitoredEndpoint;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity(name = "endpoint")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "endpoints")
@ToString(of = {"name", "url", "createdAt", "lastCheckAt", "monitoredInterval"})
public class MonitoredEndpointData {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "checked_at")
    private Instant lastCheckAt;

    @Column(name = "monitored_interval", nullable = false)
    private int monitoredInterval;

    public MonitoredEndpoint toDomain() {
        return MonitoredEndpoint.builder()
                .id(this.id)
                .owner(this.owner.toDomain())
                .name(this.name)
                .url(this.url)
                .createdAt(this.createdAt)
                .lastCheckAt(this.lastCheckAt)
                .monitoredInterval(this.monitoredInterval)
                .build();
    }


    public static MonitoredEndpointData from(MonitoredEndpoint endpoint) {

        return new MonitoredEndpointData(
                endpoint.getId(),
                UserData.from(endpoint.getOwner()),
                endpoint.getName(),
                endpoint.getUrl(),
                endpoint.getCreatedAt(),
                endpoint.getLastCheckAt(),
                endpoint.getMonitoredInterval()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MonitoredEndpointData that = (MonitoredEndpointData) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
