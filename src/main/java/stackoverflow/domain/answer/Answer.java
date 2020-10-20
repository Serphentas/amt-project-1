package stackoverflow.domain.answer;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {

    @Setter(AccessLevel.NONE)
    private AnswerId id;
    private QuestionId questionId;
    private PersonId personId;
    private String author;
    private String text;

    @Override
    public Answer deepClone() {
        return this.toBuilder()
            .id( new AnswerId(id.asString()))
            .build();
    }
}
