package stackoverflow.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

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
}
