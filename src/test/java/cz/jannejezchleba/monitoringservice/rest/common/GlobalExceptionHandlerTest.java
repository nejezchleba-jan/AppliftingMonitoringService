package cz.jannejezchleba.monitoringservice.rest.common;

import cz.jannejezchleba.monitoringservice.domain.exceptions.AlreadyExistsException;
import cz.jannejezchleba.monitoringservice.domain.exceptions.NotFoundException;
import cz.jannejezchleba.monitoringservice.presenter.rest.common.GlobalExceptionHandler;
import cz.jannejezchleba.monitoringservice.presenter.rest.entities.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void handleAlreadyExistsExceptionReturnsBadRequest() {
        // given
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String exceptionMessage = "Error";
        AlreadyExistsException exception = new AlreadyExistsException(exceptionMessage) {};

        // when
        ResponseEntity<ApiResponse> actual = exceptionHandler.handleAlreadyExistsException(exception);

        // then
        assertResponse(actual, exceptionMessage, httpStatus);
    }
    @Test
    public void handleDomainExceptionReturnsNotFound() {
        // given
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String exceptionMessage = "Error";
        NotFoundException exception = new NotFoundException(exceptionMessage);

        // when
        ResponseEntity<ApiResponse> actual = exceptionHandler.handleDomainException(exception);

        // then
        assertResponse(actual, exceptionMessage, httpStatus);
    }

    private void assertResponse(ResponseEntity<ApiResponse> actual, String exceptionMessage, HttpStatus httpStatus) {
        assertThat(actual.getStatusCode()).isEqualTo(httpStatus);
        assertThat(Objects.requireNonNull(actual.getBody()).getMessage()).isEqualTo(exceptionMessage);
        assertThat(actual.getBody().getSuccess()).isFalse();
    }
}
