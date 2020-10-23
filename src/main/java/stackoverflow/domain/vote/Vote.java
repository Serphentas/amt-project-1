package stackoverflow.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Getter
@Setter
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {
    private VoteId voteId;
    private PersonId personId;

    private QuestionId questionId;

    @Override
    public VoteId getId() {
        return voteId;
    }

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .voteId(new VoteId(voteId.asString()))
                .personId(new PersonId(personId.asString()))
                .questionId(new QuestionId(questionId.asString()))
                .build();
    }
}
