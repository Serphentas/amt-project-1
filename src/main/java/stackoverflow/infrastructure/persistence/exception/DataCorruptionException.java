package stackoverflow.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class DataCorruptionException extends RuntimeException {

    private String msg;
}