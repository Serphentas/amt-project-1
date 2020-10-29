package stackoverflow.domain.answer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    void setupAnswer(){
        answer = Answer.builder()
                .text("google it")
                .build();
    }

    @Test
    void deepCloneShouldGiveACopy(){
        Answer answerCopy = answer.deepClone();
        answerCopy.setText("I don't know");

        assertNotEquals(answerCopy,answer);
    }
}
