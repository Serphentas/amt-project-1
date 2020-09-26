package stackoverflow.application.identitymngmt.authenticate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
