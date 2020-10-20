package stackoverflow.application.question;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import stackoverflow.domain.question.QuestionId;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeQuestionCmd {

    @Builder.Default
    private String author = "Anonymous";

    @Builder.Default
    private String title = "No title";

    private QuestionId id;

    @Builder.Default
    private String text = "No content";
}
