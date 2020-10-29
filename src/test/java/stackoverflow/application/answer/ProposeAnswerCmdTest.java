package stackoverflow.application.answer;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProposeAnswerCmdTest {

    @Test
    public void questionIdShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeAnswerCmd.builder()
                    .personId(new PersonId())
                    .text("google it")
                    .build();
        });
    }

    @Test
    public void personIdShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeAnswerCmd.builder()
                    .questionId(new QuestionId())
                    .text("google it")
                    .build();
        });
    }

    @Test
    public void textIdShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeAnswerCmd.builder()
                    .personId(new PersonId())
                    .questionId(new QuestionId())
                    .build();
        });
    }

}
