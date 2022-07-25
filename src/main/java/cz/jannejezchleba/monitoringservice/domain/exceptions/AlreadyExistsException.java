package cz.jannejezchleba.monitoringservice.domain.exceptions;

public class AlreadyExistsException extends DomainException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}