package stackoverflow.application.identitymngmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCmd {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String clearTextPassword;
    private String confirmPassword;
}
