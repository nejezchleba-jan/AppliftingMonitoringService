package cz.jannejezchleba.monitoringservice.presenter.rest.entities;

import lombok.Value;

@Value
public class ApiResponse {
    Boolean success;
    String message;
}
