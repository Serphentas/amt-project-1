package stackoverflow.application.comment;

import java.util.List;

import lombok.*;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class CommentsDTO {
    @Builder
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class CommentDTO {
        @NonNull
        private CommentId id;
        @NonNull
        private PersonId personId;

        private AnswerId answerId;
        private QuestionId questionId;
        @NonNull
        private String text;
        private String author;
    }

    @Singular
    private List<CommentDTO> comments;
}
