package stackoverflow.domain.person;

import lombok.Value;

@Value
public class FailedHashingPasswordException extends RuntimeException {

    private String msg;
}
