package stackoverflow.application.identitymngmt.login;

import lombok.Value;

@Value
public class RegistrationFailedException extends Exception {

    private String msg;
}
