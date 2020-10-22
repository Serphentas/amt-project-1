package stackoverflow.application.question;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.tag.Tag;

import java.util.Collection;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeQuestionCmd {

    private PersonId personId;

    @Builder.Default
    private String title = "No title";

    private QuestionId id;
    private Collection<Tag> tags;


    @Builder.Default
    private String text = "No content";
}
