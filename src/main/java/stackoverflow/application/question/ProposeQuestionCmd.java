package stackoverflow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ProposeQuestionCmd {

    @Builder.Default
    private String author = "Anonymous";

    @Builder.Default
    private String title = "No title";

    @Builder.Default
    private String text = "No content";
}
