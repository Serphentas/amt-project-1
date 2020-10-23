package stackoverflow.domain;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuestionTest {

    @Test
    void questionIdShouldBeGeneratedAuto(){
        Question q = Question.builder()
            .title("test")
            .text("test")
            .build();
        assertNotEquals(null,q.getId());
    }

    @Test
    void questionsTalkingAboutSexAreClassifiedAdult(){
        Question q = Question.builder()
            .text("sex")
            .title("test")
            .author("123")
            .build();
        assertEquals(QuestionType.ADULT,q.getQuestionType());
    }

    @Test
    void questionsCanBeClassified(){
        Question q = Question.builder()
            .title("test")
            .text("c++")
            .build();
        q.categorizeAs(QuestionType.CODING);
        assertEquals(QuestionType.CODING,q.getQuestionType());
    }
}
