package stackoverflow.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeAnswerCmd {

    @NonNull
    private AnswerId id;
    @NonNull
    private QuestionId questionId;
    @NonNull
    private PersonId personId;
    @NonNull
    private String text;

}
