package stackoverflow.application.comment;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class CommentQuery {
    @Builder.Default
    private PersonId id = null;

    @Builder.Default
    private PersonId idUser = null;

    @Builder.Default
    private AnswerId idAnswer = null;

    @Builder.Default
    private QuestionId idQuestion = null;

    @Builder.Default
    private String text = null;
}