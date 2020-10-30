package stackoverflow.application.comment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.question.Question;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;
import stackoverflow.infrastructure.persistence.jdbc.JdbcCommentRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentfacadeIT {
    static CommentFacade commentFacade;
    static Person author;
    static Question question;
    static Question question2;
    static Answer answer;
    static Connection con;

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

        answer = Answer.builder()
            .questionId(question.getId())
            .personId(author.getId())
            .text("google it")
            .author(author.getUsername())
            .build();

        DataSource ds = DataSourceProvider.getDataSource();
        con = ds.getConnection();

        con.prepareStatement("DELETE FROM Comment").execute();
        con.prepareStatement("DELETE FROM Answer").execute();
        con.prepareStatement("DELETE FROM Question").execute();
        con.prepareStatement("DELETE FROM User").execute();

        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO codemad.User VALUES (?, 'Rabbit', 'alice', 'Wonderland', 'alice.wonderland@gmail.com', 'Pa$$w0rd')");
        statement.setString(1, author.getId().asString());
        statement.execute();

        statement = con.prepareStatement(
                "INSERT INTO codemad.Question VALUES (?, ?, 'question', 'why')");
        statement.setString(1, question.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        statement = con.prepareStatement(
                "INSERT INTO codemad.Question VALUES (?, ?, 'question2', 'where')");
        statement.setString(1, question2.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        statement = con.prepareStatement(
                "INSERT INTO codemad.Answer VALUES (?,?,?, 'google it')");
        statement.setString(1, answer.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.setString(3, question.getId().asString());
        statement.execute();

        commentFacade = new CommentFacade( new JdbcCommentRepository(ds));
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        con.prepareStatement("DELETE FROM Answer").execute();
        con.prepareStatement("DELETE FROM Question").execute();
        con.prepareStatement("DELETE FROM User").execute();

    }

    @AfterEach
    public void cleanCommentBDD() throws SQLException {
        con.prepareStatement("DELETE FROM Comment").execute();
    }

    @Test
    public void proposeCommentShouldPutAnEntryInBDD() {
        ProposeCommentCmd command = ProposeCommentCmd.builder()
                .personId(author.getId())
                .questionId(question.getId())
                .answerId(null)
                .text("noob")
                .build();
        commentFacade.proposeComment(command);
    }
}
