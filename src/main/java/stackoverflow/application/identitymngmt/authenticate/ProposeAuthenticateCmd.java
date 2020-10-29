package stackoverflow.application.identitymngmt.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeAuthenticateCmd {
    @NonNull
    private String username;
    @NonNull
    private String clearTextPassword;
}
