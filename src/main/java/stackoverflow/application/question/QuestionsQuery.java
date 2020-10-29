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
    private UUID id = null;

    @Builder.Default
    private UUID idUser = null;

    @Builder.Default
    private String author = null;

    @Builder.Default
    private String title = null;

    @Builder.Default
    private String text = null;
}
