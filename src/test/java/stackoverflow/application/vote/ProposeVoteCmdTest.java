package stackoverflow.application.vote;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.VoteId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProposeVoteCmdTest {

    @Test
    public void personIdShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeVoteCmd.builder()
                    .questionId(new QuestionId())
                    .build();
        });
    }

    @Test
    public void allForeignIdShouldBeNullByDefault() {
        ProposeVoteCmd cmd = ProposeVoteCmd.builder()
                .personId(new PersonId())
                .build();
        assertEquals(null, cmd.getQuestionId());
        //todo uncomment
        //assertEquals(null, cmd.getCommentId());
    }
}
