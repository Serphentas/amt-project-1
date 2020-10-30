package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JdbcAnswerRepositoryIT {
    static JdbcAnswerRepository repo;
    static Person author;
    static Question question1;
    static Question question2;

    @BeforeAll
    public static void setupRepoAndBDD() throws SQLException {
        author = Person.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();

        question1 = Question.builder()
                .title("question1")
                .text("what")
                .author(author.getUsername())
                .userId(author.getId())
                .build();

        question2 = Question.builder()
                .title("question2")
                .text("who")
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
                "INSERT INTO codemad.Question VALUES (?, ?, 'question1', 'what')");
        statement.setString(1, question1.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.Question VALUES (?, ?, 'question2', 'who')");
        statement.setString(1, question2.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        repo = new JdbcAnswerRepository(ds);
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Answer").execute();
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Question").execute();
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM User").execute();
    }

    @AfterEach
    public void cleanAnswerBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Answer").execute();
    }

    @Test
    public void saveShouldPutAnEntryInDBB() throws SQLException {
        Answer expectedAnswer = Answer.builder()
            .questionId(question1.getId())
            .author(author.getUsername())
            .personId(author.getId())
            .text("google it")
            .build();


        repo.save( expectedAnswer);
        PreparedStatement statement = DataSourceProvider.getDataSource().getConnection().prepareStatement("SELECT * FROM Answer WHERE idAnswer=?");
        statement.setString(1,expectedAnswer.getId().asString());
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            Answer answer = Answer.builder()
                    .id(new AnswerId(rs.getString("idAnswer")))
                    .questionId(new QuestionId(rs.getString("idQuestion")))
                    .author(author.getUsername())
                    .personId(new PersonId(rs.getString("idUser")))
                    .text(rs.getString("text"))
                    .build();
            assertTrue(expectedAnswer.equals(answer));
        }
    }

    @Test
    public void findReturnOnlyAnswerMatchingQuery() {
        Answer expectedAnswer1 = Answer.builder()
                .questionId(question1.getId())
                .author(author.getUsername())
                .personId(author.getId())
                .text("google it")
                .build();

        Answer expectedAnswer2 = Answer.builder()
                .questionId(question1.getId())
                .author(author.getUsername())
                .personId(author.getId())
                .text("google it")
                .build();

        Answer expectedAnswer3 = Answer.builder()
                .questionId(question2.getId())
                .author(author.getUsername())
                .personId(author.getId())
                .text("google it")
                .build();

        repo.save( expectedAnswer1);
        repo.save( expectedAnswer2);
        repo.save( expectedAnswer3);

        ArrayList<Answer> answers = (ArrayList) repo.find(
            AnswersQuery.builder().id(question1.getId()).build()
        );

        assertEquals( 2, answers.size());
        assertTrue(expectedAnswer1.equals(answers.get(1)));
        assertTrue(expectedAnswer2.equals(answers.get(0)));
    }

}
