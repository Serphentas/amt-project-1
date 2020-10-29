package stackoverflow.application.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFacadeIT {
/*
    private QuestionFacade questionFacade;
    private QuestionId id;
    private ProposeQuestionCmd cmd;

    @BeforeEach
    void setupQuestionFacade() {
        this.questionFacade = new QuestionFacade( new MemoryQuestionRepo());
        id = new QuestionId("1c40316e-163d-11eb-adc1-0242ac120002");
    }

    @Test
    void iCanUseProposeQuestion() {
        cmd = ProposeQuestionCmd.builder()
            .id( id)
            .userId( new PersonId())
            .title("test")
            .text("Bla bla bla")
            .build();

        assertDoesNotThrow( () -> {
            questionFacade.proposeQuestion(cmd);
        });
    }

    @Test
    void iCanUseGetAllQuestions() {
        iCanUseProposeQuestion();

        QuestionsDTO questions = questionFacade.getAllQuestions();

        assertNotNull(questions);
        assertEquals(1, questions.getQuestions().size());
        assertEquals(cmd.getText(), questions.getQuestions().get(0).getText());
    }

    @Test
    void iCanUseGetQuestionById() {
        iCanUseProposeQuestion();
        QuestionsDTO.QuestionDTO question = questionFacade.getQuestionById( id);

        assertNotNull(question);
        assertEquals(question.getText(), question.getText());
    }

    @Test
    void iCanUseGetQuestions() {
        iCanUseProposeQuestion();
        //todo
    }
 */
}
