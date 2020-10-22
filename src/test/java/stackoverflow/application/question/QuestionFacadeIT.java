package stackoverflow.application.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import stackoverflow.application.question.ProposeQuestionCmd;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.infrastructure.persistence.memory.MemoryQuestionRepo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionFacadeIT {

    private QuestionFacade questionFacade;

    @BeforeEach
    void setupQuestionFacade(){
        IQuestionRepo questionRepo = new MemoryQuestionRepo();
        this.questionFacade = new QuestionFacade(questionRepo);
    }

    @Test
    void publishQuestion(){
        ProposeQuestionCmd cmd = ProposeQuestionCmd.builder()
            .personId(new PersonId())
            .text("Bla bla bla")
            .build();
        questionFacade.proposeQuestion(cmd);
        QuestionsDTO view = questionFacade.getQuestions();

        assertNotNull(view);
        assertEquals(1, view.getQuestions().size());
        assertEquals(cmd.getText(), view.getQuestions().get(0).getText());
    }

}
