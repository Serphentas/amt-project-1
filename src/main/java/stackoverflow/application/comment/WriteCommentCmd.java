package stackoverflow.application.comment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class WriteCommentCmd {
    private PersonId idUser;
    private AnswerId idAnswer;
    private QuestionId idQuestion;
    private String text;
}