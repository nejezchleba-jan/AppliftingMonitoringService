package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
public class MonitoredEndpointRequest {
    @NotBlank
    String name;

    @NotBlank
    @Pattern(
            regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)",
            message = "Invalid url address")
    String url;

    @NonNull
    @Min(5) // Min 5 secs intervals
    int monitoredInterval;
}
