package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.tag.Tag;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcQuestionRepositoryIT {
    static JdbcQuestionRepository repo;
    static Person author;
    static Connection con;

    @BeforeAll
    public static void setup() throws SQLException {
        author = Person.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();

        DataSource ds = DataSourceProvider.getDataSource();
        con = ds.getConnection();
        
        con.prepareStatement("DELETE FROM Question;").execute();
        con.prepareStatement("DELETE FROM User;").execute();

        PreparedStatement statement = con.prepareStatement(
            "INSERT INTO codemad.User VALUES (?, 'Rabbit', 'alice', 'Wonderland', 'alice.wonderland@gmail.com', 'Pa$$w0rd')");
        statement.setString(1, author.getId().asString());
        statement.execute();

        repo = new JdbcQuestionRepository(ds);
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        con.prepareStatement("DELETE FROM Question;").execute();
        con.prepareStatement("DELETE FROM User").execute();
    }

    @Test
    public void findByIdReturnNullWhenNoQuestionIsFound(){
        Question nonExistingQuestion = repo.findById(new QuestionId()).orElse(null);
        assertNull(nonExistingQuestion);
    }


    @Test
    public void saveShouldPutAnEntryInDBB(){
        Question expectedQuestion= Question.builder()
            .title("question")
            .text("why")
            .author("Rabbit")
            .userId(author.getId())
            .build();

        repo.save( expectedQuestion);
        Question question = repo.findById(expectedQuestion.getId()).orElse(null);
        assertFalse(expectedQuestion.equals(question));
    }

    @Test
    public void findAllReturnAllQuestion(){
        repo.save( Question.builder()
                .title("question")
                .text("why")
                .author("Rabbit")
                .userId(author.getId())
                .build()
        );
        repo.save( Question.builder()
                .title("question")
                .text("who")
                .author("Rabbit")
                .userId(author.getId())
                .build()
         );
        repo.save( Question.builder()
                .title("question")
                .text("where")
                .author("Rabbit")
                .userId(author.getId())
                .build()
        );
        assertEquals(3, repo.findAll().size());
    }

    @Test
    public void findReturnQuestionMatchingQuery(){
        //todo
    }
}
