package stackoverflow.domain.question;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.tag.Tag;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    @Builder.Default
    private QuestionId id = new QuestionId();
    @NonNull
    private PersonId userId;

    @Builder.Default
    private String author = null;

    @NonNull
    private String title;
    @NonNull
    private String text;

    @Builder.Default
    private Collection<Tag> tags = null;

    @Override
    public Question deepClone() {
        return this.toBuilder()
            .id(new QuestionId(id.asString()))
            .build();
    }
}
