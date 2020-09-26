package stackoverflow.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class IntegrityConstraintViolationException extends Exception {

    public IntegrityConstraintViolationException(String msg) { super(msg); }
}
