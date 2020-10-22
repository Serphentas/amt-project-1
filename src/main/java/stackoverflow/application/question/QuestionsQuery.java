package stackoverflow.application.question;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class QuestionsQuery {

    @Builder.Default
    private UUID idQuestion = null;

    @Builder.Default
    private UUID idUser = null;

    @Builder.Default
    private String title = "No title";

    @Builder.Default
    private String text = "No text";

    @Builder.Default
    private boolean safeForChildren = true;
}
