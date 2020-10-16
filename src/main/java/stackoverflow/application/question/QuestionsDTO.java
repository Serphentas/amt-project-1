package stackoverflow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import stackoverflow.domain.question.QuestionId;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO{
        private String title;
        private String text;
        private QuestionId id;
    }

    @Singular
    private List<QuestionDTO> questions;
}
