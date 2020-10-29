package stackoverflow.application.question;

import org.junit.jupiter.api.Test;

import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import static org.junit.jupiter.api.Assertions.*;

public class ProposeQuestionCmdTest {

    @Test
    public void personIdShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeQuestionCmd.builder()
                    .title("title")
                    .text("question")
                    .build();
        });
    }

    @Test
    public void textShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeQuestionCmd.builder()
                    .userId(new PersonId())
                    .title("title")
                    .build();
        });
    }

    @Test
    public void titleShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeQuestionCmd.builder()
                    .userId(new PersonId())
                    .text("question")
                    .build();
        });
    }


    @Test
    public void tagsShouldBeNullByDefault() {
        ProposeQuestionCmd cmd = ProposeQuestionCmd.builder()
                .userId(new PersonId())
                .title("title")
                .text("question")
                .build();
        assertEquals(null, cmd.getTags());
    }
}
