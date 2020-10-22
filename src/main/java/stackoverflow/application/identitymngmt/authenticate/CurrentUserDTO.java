package stackoverflow.application.identitymngmt.authenticate;

import lombok.Builder;
import lombok.Value;
import stackoverflow.domain.person.PersonId;

@Value
@Builder
public class CurrentUserDTO {
    private PersonId id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
