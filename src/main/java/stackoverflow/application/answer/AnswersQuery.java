package stackoverflow.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@EqualsAndHashCode
public class AnswersQuery {

    @Builder.Default
    public QuestionId id = null;
}
