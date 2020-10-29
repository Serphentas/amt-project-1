package stackoverflow.application.question;

import lombok.*;

import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
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
    private QuestionId id;
    @NonNull
    private String author;
    @NonNull
    private String title;
    @NonNull
    private String text;

    @Builder.Default
    private Collection<Tag> tags = null;

}
