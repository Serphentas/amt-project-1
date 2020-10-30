package stackoverflow.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.AnswerId;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class AnswersDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class AnswerDTO{
        private AnswerId id;
        private String text;
        private String author;
        private int votes;
    }

    @Singular
    private List<AnswerDTO> answers;
}
