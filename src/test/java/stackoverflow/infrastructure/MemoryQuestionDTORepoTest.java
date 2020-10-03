package stackoverflow.infrastructure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import stackoverflow.domain.question.Question;

public class MemoryQuestionDTORepoTest {

    @Test
    void removeNonExistingQuestionDontThrow(){

    }

    @Test
    void findByIdReturnCopy(){
        Question.QuestionBuilder questionBuilder = Question.builder()
                .author("Rabbit")
                .text(" random text");

        Question question = questionBuilder.build();
        Question questionCopy = question.deepClone();
        questionCopy.setAuthor("RabbitFriend");

        assertNotEquals(questionCopy,question);
    }

    @Test
    void findWithNonExistingIdReturnEmpty(){

    }

    @Test
    void findAllReturnsCopies(){
    }

    @Test
    void removeAnExistingQuestion(){

    }
}