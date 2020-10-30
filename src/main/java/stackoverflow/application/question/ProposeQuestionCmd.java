package stackoverflow.application.question;

import lombok.*;

import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.tag.Tag;

import java.util.Collection;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ProposeQuestionCmd {
    @NonNull
    private PersonId userId;

    @NonNull
    private String title;
    @NonNull
    private String text;

    @Builder.Default
    private Collection<Tag> tags = null;

}
