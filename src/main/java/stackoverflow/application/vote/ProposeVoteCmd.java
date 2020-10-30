package stackoverflow.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeVoteCmd {
    @NonNull
    private PersonId personId;

    @Builder.Default
    private AnswerId answerId = null;

    @Builder.Default
    private QuestionId questionId = null;

    @Builder.Default
    private CommentId commentId = null;
}
