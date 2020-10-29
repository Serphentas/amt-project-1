package stackoverflow.application.identitymngmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeRegisterCmd {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String clearTextPassword;
    @NonNull
    private String confirmPassword;
}
