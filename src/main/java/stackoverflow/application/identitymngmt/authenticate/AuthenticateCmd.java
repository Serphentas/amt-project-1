package stackoverflow.application.identitymngmt.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AuthenticateCmd {
    private String username;
    private String clearTextPassword;
}
