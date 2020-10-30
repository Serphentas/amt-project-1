package stackoverflow.application.identitymngmt;

import lombok.*;
import stackoverflow.domain.person.PersonId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UpdateProfilCmd {
    private PersonId personId;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
