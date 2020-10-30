package stackoverflow.domain.comment;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Getter
@Setter
@Builder(toBuilder = true)
public class Comment implements IEntity<Comment, CommentId> {

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private CommentId id = new CommentId();

    @NonNull
    private PersonId personId;


    @Builder.Default
    private AnswerId answerId = null;

    @Builder.Default
    private QuestionId questionId = null;

    @NonNull
    private String text;

    private String author;

    @Override
    public Comment deepClone() {
        return this.toBuilder()
            .id(new CommentId(id.asString()))
            .build();
    }
}
