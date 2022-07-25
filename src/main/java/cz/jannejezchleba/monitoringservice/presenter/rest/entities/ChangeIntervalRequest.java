package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Min;

@Value
public class ChangeIntervalRequest {
    @NonNull
    @Min(5)
    int monitoredInterval;

    @JsonCreator
    public ChangeIntervalRequest(@JsonProperty("monitoredInterval") int monitoredInterval) {
        this.monitoredInterval = monitoredInterval;
    }
}