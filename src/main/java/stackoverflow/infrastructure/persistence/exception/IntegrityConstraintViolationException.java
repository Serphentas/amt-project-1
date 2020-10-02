package stackoverflow.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class IntegrityConstraintViolationException extends RuntimeException {

    private String msg;
}
