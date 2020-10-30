package stackoverflow.application.question;

import org.junit.jupiter.api.*;

import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;
import stackoverflow.infrastructure.persistence.jdbc.JdbcQuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFacadeIT {

    static ProposeQuestionCmd cmd;

    static QuestionFacade questionFacade;
    static Connection con;

    @BeforeAll
    public static void setupFacade() throws SQLException {
        Person author = Person.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();

        cmd = ProposeQuestionCmd.builder()
                .userId( author.getId())
                .title("test")
                .text("Bla bla bla")
                .build();

        questionFacade = new QuestionFacade(new JdbcQuestionRepository(DataSourceProvider.getDataSource()));

        con = DataSourceProvider.getDataSource().getConnection();

        con.prepareStatement("DELETE FROM Question;").execute();
        con.prepareStatement("DELETE FROM User;").execute();

        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO codemad.User VALUES (?, 'Rabbit', 'alice', 'Wonderland', 'alice.wonderland@gmail.com', 'Pa$$w0rd')");
        statement.setString(1, author.getId().asString());

        statement.execute();
    }


    @AfterAll
    public static void cleanDBB() throws SQLException {
        con.prepareStatement("DELETE FROM Question").execute();
        con.prepareStatement("DELETE FROM User").execute();
    }

    @Test
    void iCanUseProposeQuestion() {
        assertDoesNotThrow( () -> {
            questionFacade.proposeQuestion(cmd);
        });
    }

    @Test
    void iCanUseGetAllQuestions() {
        QuestionsDTO questions = questionFacade.getAllQuestions();

        assertNotNull(questions);
        assertEquals(1, questions.getQuestions().size());
        assertEquals(cmd.getText(), questions.getQuestions().get(0).getText());
    }

    @Test
    void iCanUseGetQuestionById() {
        QuestionId id = questionFacade.getAllQuestions().getQuestions().get(0).getId();
        QuestionsDTO.QuestionDTO question = questionFacade.getQuestionById( id);

        assertNotNull(question);
        assertEquals(question.getText(), question.getText());
    }

    @Test
    void iCanUseGetQuestions() {
        //todo
    }

}
