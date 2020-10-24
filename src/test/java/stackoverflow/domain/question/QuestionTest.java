package stackoverflow.domain.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuestionTest {

    private Question question;

    @BeforeEach
    void setupQuestion(){
        question = Question.builder()
                .title("it's a title")
                .text("test")
                .build();
    }

    @Test
    void questionIdShouldBeGeneratedAuto(){
        assertNotEquals(null,question.getId());
    }

    @Test
    void deepCloneShouldGiveACopy(){
        Question questionCopy = question.deepClone();
        questionCopy.setTitle("another title");

        assertNotEquals(questionCopy,question);
    }
}
