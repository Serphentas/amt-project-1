package stackoverflow.application.identitymngmt.authenticate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
