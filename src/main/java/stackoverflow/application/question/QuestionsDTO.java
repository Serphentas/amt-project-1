package stackoverflow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class QuestionDTO{
        private QuestionId id;
        private PersonId userId;
        private String author;
        private String title;
        private String text;
    }

    @Singular
    private List<QuestionDTO> questions;
}
