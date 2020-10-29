package stackoverflow.application.answer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.question.Question;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;
import stackoverflow.infrastructure.persistence.jdbc.JdbcAnswerRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerFacadeIT {
    static AnswerFacade answerFacade;
    static Person author;
    static Question question;
    static Question question2;

    @BeforeAll
    public static void setupanswerFacadeAndBDD() throws SQLException {
        author = Person.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();

        question = Question.builder()
                .title("question")
                .text("why")
                .author(author.getUsername())
                .userId(author.getId())
                .build();

        question2 = Question.builder()
                .title("question2")
                .text("where")
                .author(author.getUsername())
                .userId(author.getId())
                .build();

        DataSource ds = DataSourceProvider.getDataSource();

        ds.getConnection().prepareStatement("DELETE FROM Answer").execute();
        ds.getConnection().prepareStatement("DELETE FROM Question").execute();
        ds.getConnection().prepareStatement("DELETE FROM User").execute();

        PreparedStatement statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.User VALUES (?, 'Rabbit', 'alice', 'Wonderland', 'alice.wonderland@gmail.com', 'Pa$$w0rd')");
        statement.setString(1, author.getId().asString());
        statement.execute();

        statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.Question VALUES (?, ?, 'question', 'why')");
        statement.setString(1, question.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.Question VALUES (?, ?, 'question2', 'where')");
        statement.setString(1, question2.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        answerFacade = new AnswerFacade( new JdbcAnswerRepository(ds));
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Question").execute();
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM User").execute();
    }

    @AfterEach
    public void cleanAnswerBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Answer").execute();
    }

    @Test
    public void proposeAnswerCmdShouldPutAnEntryInDBB() {
        ProposeAnswerCmd expectedAnswer = ProposeAnswerCmd.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("google it")
                .build();


        answerFacade.proposeAnswer( expectedAnswer);
        AnswersDTO answers = answerFacade.getAnswers(AnswersQuery.builder().id(question.getId()).build());
        assertFalse(expectedAnswer.equals(answers.getAnswers().get(0)));
    }

    @Test
    public void getAnswersReturnOnlyAnswerMatchingQuery() {
        ProposeAnswerCmd expectedAnswer1 = ProposeAnswerCmd.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("google it")
                .build();

        ProposeAnswerCmd expectedAnswer2 = ProposeAnswerCmd.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("google it")
                .build();

        ProposeAnswerCmd expectedAnswer3 = ProposeAnswerCmd.builder()
                .questionId(question2.getId())
                .personId(author.getId())
                .text("google it")
                .build();

        answerFacade.proposeAnswer( expectedAnswer1);
        answerFacade.proposeAnswer( expectedAnswer2);
        answerFacade.proposeAnswer( expectedAnswer3);

        AnswersDTO answers = answerFacade.getAnswers(AnswersQuery.builder().id(question.getId()).build());

        assertEquals(2, answers.getAnswers().size());
        assertTrue(
                expectedAnswer1.getText().equals(answers.getAnswers().get(1).getText())
        );
        assertTrue(
                expectedAnswer2.getText().equals(answers.getAnswers().get(0).getText())
        );
    }

}
