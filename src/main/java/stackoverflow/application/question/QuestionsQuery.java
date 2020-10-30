package stackoverflow.application.question;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class QuestionsQuery {

    @Builder.Default
    private QuestionId id = null;

    @Builder.Default
    private PersonId idUser = null;

    @Builder.Default
    private String author = null;

    @Builder.Default
    private String title = null;

    @Builder.Default
    private String text = null;
}
