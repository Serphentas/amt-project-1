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

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private CommentId id = new CommentId();

    @NonNull
    private PersonId personId;


    @Builder.Default
    private AnswerId answerId = null;

    @Builder.Default
    private QuestionId questionId = null;

    @NonNull
    private String text;

    @Builder.Default
    private String author = null;

    @Override
    public Comment deepClone() {
        return this.toBuilder()
            .id(new CommentId(id.asString()))
            .build();
    }
}
