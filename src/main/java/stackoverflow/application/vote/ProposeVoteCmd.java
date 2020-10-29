package stackoverflow.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.VoteId;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeVoteCmd {
    @NonNull
    private PersonId personId;
    @NonNull
    private VoteId id;

    @Builder.Default
    private QuestionId questionId = null;

    //@Builder.Default
    //private CommentId commentId = null;
}
