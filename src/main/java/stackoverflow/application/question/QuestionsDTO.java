package stackoverflow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

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
        private UUID idQuestion;
        private UUID idUser;
        private String title;
        private String text;
        private boolean safeForChildren;
    }

    @Singular
    private List<QuestionDTO> questions;
}
