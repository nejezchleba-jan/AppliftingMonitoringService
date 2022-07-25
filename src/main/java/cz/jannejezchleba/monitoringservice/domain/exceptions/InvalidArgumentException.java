package cz.jannejezchleba.monitoringservice.domain.exceptions;

public class InvalidArgumentException extends DomainException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
