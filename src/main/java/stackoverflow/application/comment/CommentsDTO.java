package stackoverflow.application.comment;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
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
        private CommentId id;
        private PersonId idUser;
        private AnswerId idAnswer;
        private QuestionId idQuestion;
        private String text;
    }

    @Singular
    private List<CommentDTO> comments;
}
