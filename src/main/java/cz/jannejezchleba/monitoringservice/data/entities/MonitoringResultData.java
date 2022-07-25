package cz.jannejezchleba.monitoringservice.data.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.monitoring.MonitoringResult;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@AllArgsConstructor
@Entity(name = "result")
@Getter
@NoArgsConstructor
@Setter
@Table(name = "monitoring_results")
@ToString(of = {"checkedAt", "httpStatusCode", "returnedPayload"})
public class MonitoringResultData {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
    private int id;

    @Column(name = "checked_at", nullable = false)
    private Instant checkedAt;

    @Column(name = "http_status_code", nullable = false)
    private int httpStatusCode;

    @Column(name = "payload")
    @Lob
    private String returnedPayload;

    @ManyToOne
    @JoinColumn(name = "endpoint_id", nullable = false)
    private MonitoredEndpointData monitoredEndpoint;

    public MonitoringResult toDomain() {
        return MonitoringResult.builder()
                .id(this.id)
                .checkedAt(this.checkedAt)
                .httpStatusCode(this.httpStatusCode)
                .returnedPayload(this.returnedPayload)
                .monitoredEndpoint(this.monitoredEndpoint.toDomain())
                .build();
    }

    public static MonitoringResultData from(MonitoringResult endpoint) {

        return new MonitoringResultData(
                endpoint.getId(),
                endpoint.getCheckedAt(),
                endpoint.getHttpStatusCode(),
                endpoint.getReturnedPayload(),
                MonitoredEndpointData.from(endpoint.getMonitoredEndpoint())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MonitoringResultData that = (MonitoringResultData) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
