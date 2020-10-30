package stackoverflow.domain.vote;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Getter
@Setter
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private VoteId voteId = new VoteId();
    @NonNull
    private PersonId personId;

    @Builder.Default
    private AnswerId answerId=null;
    @Builder.Default
    private QuestionId questionId=null;
    @Builder.Default
    private CommentId commentId=null;

    @Override
    public VoteId getId() {
        return voteId;
    }

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .voteId(new VoteId(voteId.asString()))
                .personId(new PersonId(personId.asString()))
                .build();
    }

}
