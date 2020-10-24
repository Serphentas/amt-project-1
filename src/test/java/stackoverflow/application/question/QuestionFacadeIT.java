package stackoverflow.application.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stackoverflow.application.question.QuestionsDTO.QuestionDTO;
import stackoverflow.application.question.ProposeQuestionCmd;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.infrastructure.persistence.memory.MemoryQuestionRepo;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFacadeIT {

    private QuestionFacade questionFacade;
    private QuestionId id;
    private ProposeQuestionCmd cmd;

    @BeforeEach
    void setupQuestionFacade(){
        this.questionFacade = new QuestionFacade( new MemoryQuestionRepo());
        id = new QuestionId("1c40316e-163d-11eb-adc1-0242ac120002");
    }

    @Test
    void iCanUseProposeQuestion() {
        cmd = ProposeQuestionCmd.builder()
            .id( id)
            .idUser( new PersonId())
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

    @Test
    void iCanUseQuestionListAsDTOList() {
        //todo est ce que cette fonction est utile? et est elle au bon endroit?
    }
}
