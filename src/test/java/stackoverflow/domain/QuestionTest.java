package stackoverflow.domain;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuestionTest {

    @Test
    void questionIdShouldBeGeneratedAuto(){
        Question q = Question.builder().build();
        assertNotEquals(null,q.getId());
    }

    @Test
    void questionsTalkingAboutSexAreClassifiedAdult(){
        Question q = Question.builder()
            .text("sex")
            .build();
        assertEquals(QuestionType.ADULT,q.getQuestionType());
    }

    @Test
    void questionsCanBeClassified(){
        Question q = Question.builder()
                .text("c++")
                .build();
        q.categorizeAs(QuestionType.CODING);
        assertEquals(QuestionType.CODING,q.getQuestionType());
    }
}
