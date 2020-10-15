package stackoverflow.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeAnswerCmd {

    @Builder.Default
    private String author = "Anonymous";

    @Builder.Default
    private String text = "No content";
}
