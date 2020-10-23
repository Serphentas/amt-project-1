package stackoverflow.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.VoteId;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeVoteCmd {
    @Builder.Default
    private QuestionId questionId = null;
    private PersonId personId;
    private VoteId id;
    //@Builder.Default
    //priate CommentId commentId = null;
}
