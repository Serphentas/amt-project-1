package stackoverflow.application.identitymngmt;

import lombok.*;
import stackoverflow.domain.person.PersonId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UpdateProfilCmd {
    @Setter(AccessLevel.NONE)
    private PersonId personId;

    private String firstName;
    private String lastName;
    private String email;
}
