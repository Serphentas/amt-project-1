package stackoverflow.domain.answer;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.AnswerId;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerIdTest {
    @Test
    void iCanUseAnswerId(){
        AnswerId id = new AnswerId();
        Answer answer = Answer.builder()
                .id(id)
                .text("google it!")
                .build();

        assertEquals(id, answer.getId());
    }

    @Test
    void nullPassedToConstructor(){
        try {
            AnswerId id1 = new AnswerId((String) null);
            fail();
        } catch (Exception e){}

        try {
            AnswerId id1 = new AnswerId((UUID) null);
            fail();
        } catch (Exception e){}
    }

    @Test
    void invalidStringPassedToConstructor(){
        try {
            AnswerId id = new AnswerId("invalid");
            fail();
        } catch (Exception e){}
    }

    @Test
    void iCanTurnInString(){
        AnswerId id = new AnswerId();
        AnswerId id2 = new AnswerId(id.asString());

        assertEquals(id.asString(), id2.asString());
    }
}
