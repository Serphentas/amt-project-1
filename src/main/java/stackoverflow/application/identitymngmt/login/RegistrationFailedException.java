package stackoverflow.application.identitymngmt.login;

import lombok.Value;

@Value
public class RegistrationFailedException extends RuntimeException {

    private String msg;
}
