package stackoverflow.domain;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionIdTest {
    @Test
    void iCanUseQuestionId(){
        QuestionId id = new QuestionId();
        Question q = Question.builder()
            .id(id)
            .text("")
            .build();

        assertEquals(id,q.getId());
    }

    @Test
    void nullPassedToConstructor(){
        try {
            QuestionId id1 = new QuestionId((String) null);
            fail();
        } catch (Exception e){}

        try {
            QuestionId id1 = new QuestionId((UUID) null);
            fail();
        } catch (Exception e){}
    }

    @Test
    void invalidStringPassedToConstructor(){
        try {
            QuestionId id = new QuestionId("invalid");
            fail();
        } catch (Exception e){}
    }

    @Test
    void iCanTurnIdInString(){
        QuestionId id = new QuestionId();
        QuestionId id2 = new QuestionId(id.asString());

        assertEquals(id.asString(), id2.asString());
    }
}
