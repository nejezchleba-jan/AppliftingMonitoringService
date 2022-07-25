package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;


@Value
public class RenameRequest {
    @NotBlank
    String name;

    @JsonCreator
    public RenameRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}
