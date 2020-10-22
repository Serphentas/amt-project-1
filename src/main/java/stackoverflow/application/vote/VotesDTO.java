package stackoverflow.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class VotesDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class VoteDTO{
        private int vote;
    }

    @Singular
    private List<stackoverflow.application.answer.AnswersDTO.AnswerDTO> answers;

    //todo à déplacer dans VoteDTO selon l'utilisation VotesDTO
    private PersonId personId;
    private QuestionId questionId;
    //private CommentId commentId;
}
