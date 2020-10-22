package stackoverflow.domain.vote;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Data
@Getter
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    @Setter(AccessLevel.NONE)
    private VoteId id = new VoteId();

    private PersonId userId;

    private QuestionId questionId;
    //private CommentId commentId;

    private int vote;

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .id(new VoteId(id.asString()))
                .build();
    }
}
