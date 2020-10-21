package stackoverflow.domain.vote;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Data
@Getter
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    private VoteId id;
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
