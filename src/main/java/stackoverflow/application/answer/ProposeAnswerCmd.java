package stackoverflow.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeAnswerCmd {

    private AnswerId id;
    private QuestionId questionId;
    private PersonId personId;

    @Builder.Default
    private String text = "No content";

}
