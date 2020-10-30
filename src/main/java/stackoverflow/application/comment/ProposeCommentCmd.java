package stackoverflow.application.comment;

import lombok.*;

import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ProposeCommentCmd {
    @NonNull
    private PersonId personId;
    @Builder.Default
    private AnswerId answerId = null;
    @Builder.Default
    private QuestionId questionId = null;
    @NonNull
    private String text;
}