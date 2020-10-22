package stackoverflow.application.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import stackoverflow.application.question.QuestionsDTO.QuestionDTO;
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
        this.questionFacade = new QuestionFacade(new MemoryQuestionRepo());
    }

    @Test
    void publishQuestion(){
        ProposeQuestionCmd cmd = ProposeQuestionCmd.builder()
            .author("Rabbit")
            .title("test")
            .text("Bla bla bla")
            .build();
        questionFacade.proposeQuestion(cmd);
        QuestionsDTO view = questionFacade.getAllQuestions();

        assertNotNull(view);
        assertEquals(1, view.getQuestions().size());
        assertEquals(cmd.getText(), view.getQuestions().get(0).getText());
    }

    @Test
    void getQuestionsSafeForChildren(){
        questionFacade.proposeQuestion(ProposeQuestionCmd.builder()
            .title("test safe")
            .text("safe")
            .build()
        );
        questionFacade.proposeQuestion(ProposeQuestionCmd.builder()
            .title("test also safe")
            .text("also safe")
            .build()
        );
        questionFacade.proposeQuestion(ProposeQuestionCmd.builder()
            .title("test sex")
            .text("sex")
            .build()
        );

        QuestionsDTO viewWithoutAdultQuestions = questionFacade.getQuestions(QuestionsQuery.builder()
            .safeForChildren(true)
            .build()
        );
        boolean safe = true;
        for (QuestionDTO qDTO: viewWithoutAdultQuestions.getQuestions()) {
            if (qDTO.getText().contains("sex")) {
                safe = false;
                break;
            }
        }
        assertTrue(safe);

        QuestionsDTO viewWithAdultQuestions = questionFacade.getQuestions(QuestionsQuery.builder()
            .safeForChildren(false)
            .build()
        );
        assertEquals(3, viewWithAdultQuestions.getQuestions().size());
    }
}
