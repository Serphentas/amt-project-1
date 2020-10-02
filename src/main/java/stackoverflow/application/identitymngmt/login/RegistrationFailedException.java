package stackoverflow.application.identitymngmt.login;

import lombok.Value;

@Value
public class RegistrationFailedException extends Exception {

    public RegistrationFailedException(String msg) { super(msg); }
}
