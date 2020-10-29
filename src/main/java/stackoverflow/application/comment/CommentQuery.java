package stackoverflow.application.comment;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class CommentQuery {
    @Builder.Default
    private UUID id = null;

    @Builder.Default
    private UUID idUser = null;

    @Builder.Default
    private UUID idAnswer = null;

    @Builder.Default
    private UUID idQuestion = null;

    @Builder.Default
    private String text = null;
}