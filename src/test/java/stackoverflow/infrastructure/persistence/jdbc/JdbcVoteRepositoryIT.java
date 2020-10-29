package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.vote.Vote;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcVoteRepositoryIT {
    static JdbcVoteRepository repo;
    static Person person1;
    static Person person2;
    static Question question;
    //static Comment comment;

    @BeforeAll
    public static void setupRepoAndBDD() throws SQLException {
        person1 = Person.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();

        person2 = Person.builder()
                .username("bob")
                .email("bob.bob@gmail.com")
                .firstName("bob")
                .lastName("bob")
                .clearTextPassword("Pa$$w0rd")
                .build();

        question = Question.builder()
                .title("question")
                .text("why")
                .author(person1.getUsername())
                .userId(person1.getId())
                .build();

        DataSource ds = DataSourceProvider.getDataSource();

        ds.getConnection().prepareStatement("DELETE FROM Vote").execute();
        ds.getConnection().prepareStatement("DELETE FROM Question").execute();
        ds.getConnection().prepareStatement("DELETE FROM User").execute();

        PreparedStatement statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.User VALUES (?, 'Rabbit', 'alice', 'Wonderland', 'alice.wonderland@gmail.com', 'Pa$$w0rd')");
        statement.setString(1, person1.getId().asString());
        statement.execute();

        statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.User VALUES (?, 'bob', 'bob', 'bob', 'bob.bob@gmail.com', 'Pa$$w0rd')");
        statement.setString(1, person2.getId().asString());
        statement.execute();

        statement = ds.getConnection().prepareStatement(
                "INSERT INTO codemad.Question VALUES (?, ?, 'question', 'why')");
        statement.setString(1, question.getId().asString());
        statement.setString(2, person1.getId().asString());
        statement.execute();

        repo = new JdbcVoteRepository(ds);
    }

    @AfterEach
    public void cleanVoteBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Vote").execute();
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {

        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Question").execute();
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM User").execute();
    }

    @Test
    public void toggleDeleteQuestionIfVoteExist(){
        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .questionId(question.getId())
                .build()
        );

        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .questionId(question.getId())
                .build()
        );

        assertFalse(repo.hasVotedQuestion(question.getId(),person1.getId()));
    }

    @Test
    public void toggleInsertQuestionIfVoteNotExist(){
        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .questionId(question.getId())
                .build()
        );

        assertTrue(repo.hasVotedQuestion(question.getId(),person1.getId()));
    }

    @Test
    public void hasVotedQuestionShouldReturnCorrectValue(){
        assertFalse(repo.hasVotedQuestion(question.getId(),person1.getId()));

        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .questionId(question.getId())
                .build()
        );

        assertTrue(repo.hasVotedQuestion(question.getId(),person1.getId()));
    }

    @Test
    public void nbrVoteQuestionShouldReturn0IfZeroVoteFound() {
        assertEquals( 0, repo.nbrVoteQuestion(question.getId()));
    }

    @Test
    public void nbrVoteQuestionShouldReturnCorrectNumber(){
        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .questionId(question.getId())
                .build()
        );

        repo.toggle( Vote.builder()
                .personId(person2.getId())
                .questionId(question.getId())
                .build()
        );

        assertEquals( 2, repo.nbrVoteQuestion(question.getId()));
    }
/*
//todo uncomment

    @Test
    public void toggleDeleteCommentIfVoteExist(){
        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .commentId(comment.getId())
                .build()
        );

        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .commentId(comment.getId())
                .build()
        );

        assertFalse(repo.hasVotedComment(comment.getId(),person1.getId()));
    }

    @Test
    public void toggleInsertCommentIfVoteNotExist(){
        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .commentId(comment.getId())
                .build()
        );

        assertTrue(repo.hasVotedComment(comment.getId(),person1.getId()));
    }

    @Test
    public void hasVotedCommentShouldReturnCorrectValue(){
        assertFalse(repo.hasVotedComment(comment.getId(),person1.getId()));

        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .commentId(comment.getId())
                .build()
        );

        assertTrue(repo.hasVotedComment(comment.getId(),person1.getId()));
    }

    @Test
    public void nbrVoteCommentShouldReturn0IfZeroVoteFound(){
        assertTrue( 0, repo.nbrVoteComment(comment.getId()));
    }

    @Test
    public void nbrVoteCommentShouldReturnCorrectNumber(){
        repo.toggle( Vote.builder()
                .personId(person1.getId())
                .commentId(comment.getId())
                .build()
        );

        repo.toggle( Vote.builder()
                .personId(person2.getId())
                .commentId(comment.getId())
                .build()
        );

        assertTrue( 2, repo.nbrVoteQuestion(comment.getId()));
    }
*/
}
