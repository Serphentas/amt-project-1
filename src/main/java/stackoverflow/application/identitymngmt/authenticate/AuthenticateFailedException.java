package stackoverflow.application.identitymngmt.authenticate;

import lombok.Value;

@Value
public class AuthenticateFailedException extends Exception{

    private String msg;
}
