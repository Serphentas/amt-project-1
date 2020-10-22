package stackoverflow.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.infrastructure.persistence.memory.MemoryQuestionRepo;

import java.util.Optional;

public class MemoryQuestionRepoTest {

    MemoryQuestionRepo memoryQuestionRepo;

    @BeforeEach
    void setupEmptyRepo() { memoryQuestionRepo = new MemoryQuestionRepo(); }

    @Test
    void removeNonExistingQuestionDontThrow(){
        try {
            memoryQuestionRepo.remove(new QuestionId());
        } catch (Exception e) {}
    }

    @Test
    void findByIdReturnCopy(){
        Question.QuestionBuilder questionBuilder = Question.builder()
                .author("Rabbit")
                .title("test")
                .text("random text");

        Question question = questionBuilder.build();
        Question questionCopy = question.deepClone();
        questionCopy.setAuthor("RabbitFriend");

        assertNotEquals(questionCopy,question);
    }

    @Test
    void findByIdWithNonExistingIdReturnEmpty(){
        Optional<Question> opt = memoryQuestionRepo.findById(new QuestionId());
        assertTrue(opt.isEmpty());
    }

    @Test
    void findAllReturnsCopies(){
        //todo
    }

    @Test
    void removeAnExistingQuestion(){
        //todo
    }
}
