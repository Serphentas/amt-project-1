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
    private CommentId id = new CommentId();

    private PersonId idUser;
    private AnswerId idAnswer;
    private QuestionId idQuestion;

    private String text;
    private String author;

    @Override
    public Comment deepClone() {
        return this.toBuilder()
            .id(new CommentId(id.asString()))
            .build();
    }

    public static class CommentBuilder {
        public Comment build() {
            if (id == null) {
                id = new CommentId();
            }

            if (idUser == null) {
                throw new IllegalArgumentException("User ID is mandatory for comments.");
            }

            if (idAnswer == null) {
                throw new IllegalArgumentException("Answer ID is mandatory for comments.");
            }

            if (idQuestion == null) {
                throw new IllegalArgumentException("Question ID is mandatory for comments.");
            }

            if (text == null || text.length() == 0) {
                throw new IllegalArgumentException("Text is mandatory for comments.");
            }

            if (author == null || author.length() == 0) {
                throw new IllegalArgumentException("Author is mandatory for comments.");
            }

            return new Comment(id, idUser, idAnswer, idQuestion, text, author);
        }
    }
}
