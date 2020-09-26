package stackoverflow.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class DataCorruptionException extends Exception {

    public DataCorruptionException(String msg) { super(msg); }
}