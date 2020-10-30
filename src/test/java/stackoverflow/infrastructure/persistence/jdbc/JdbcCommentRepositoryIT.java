package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.application.comment.CommentQuery;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.comment.Comment;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcCommentRepositoryIT {
    static JdbcCommentRepository repo;
    static Person author;
    static Question question;
    static Question question2;
    static Answer answer;
    static Answer answer2;
    static Connection con;

    @BeforeAll
    public static void setupRepoAndBDD() throws SQLException {
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
                .text("what")
                .author(author.getUsername())
                .userId(author.getId())
                .build();

        answer = Answer.builder()
                .personId(author.getId())
                .questionId(question.getId())
                .text("google it")
                .build();

        answer2 = Answer.builder()
                .personId(author.getId())
                .questionId(question2.getId())
                .text("google it again")
                .build();


        DataSource ds = DataSourceProvider.getDataSource();
        con = DataSourceProvider.getConnection();

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
                "INSERT INTO codemad.Question VALUES (?, ?, 'question2', 'what')");
        statement.setString(1, question2.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.execute();

        statement = con.prepareStatement(
                "INSERT INTO codemad.Answer (idAnswer,idUser,idQuestion,text) VALUES (?,?,?, 'google it')");
        statement.setString(1, answer.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.setString(3, question.getId().asString());
        statement.execute();

        statement = con.prepareStatement(
                "INSERT INTO codemad.Answer (idAnswer,idUser,idQuestion,text) VALUES (?,?,?, 'google it again')");
        statement.setString(1, answer2.getId().asString());
        statement.setString(2, author.getId().asString());
        statement.setString(3, question2.getId().asString());
        statement.execute();

        repo = new JdbcCommentRepository(ds);
    }

    @AfterEach
    public void cleanVoteBDD() throws SQLException {
        con.prepareStatement("DELETE FROM Comment").execute();
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        con.prepareStatement("DELETE FROM Answer").execute();
        con.prepareStatement("DELETE FROM Question").execute();
        con.prepareStatement("DELETE FROM User").execute();
    }

    @Test
    public void findByIdReturnNullWhenNoCommentIsFound(){
        Comment nonExistingComment = repo.findById(new CommentId()).orElse(null);
        assertNull(nonExistingComment);
    }

    @Test
    public void saveShouldPutAnEntryInBDD(){
        Comment expected = Comment.builder()
            .questionId(question.getId())
            .personId(author.getId())
            .text("you know nothing")
            .author(author.getUsername())
            .build();

        repo.save(expected);
        Comment comment = repo.findById(expected.getId()).orElse(null);
        assertTrue(expected.equals(comment));
    }

    @Test
    public void findByQuestionReturnNullWhenNoCommentIsFound(){
        ArrayList<Comment> comments = (ArrayList) repo.findByQuestion(
                CommentQuery.builder().idQuestion(question.getId()).build()
        );

        assertEquals(0,comments.size());
    }

    @Test
    public void findByAnswerIdReturnNullWhenNoCommentIsFound(){
        ArrayList<Comment> comments = (ArrayList) repo.findByAnswer(
                CommentQuery.builder().idAnswer(answer.getId()).build()
        );

        assertEquals(0,comments.size());
    }

    @Test
    public void findByAnswerReturnAllMatchingComment(){
        Comment comment1 = Comment.builder()
                .answerId(answer.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        Comment comment2 = Comment.builder()
                .answerId(answer.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        Comment comment3 = Comment.builder()
                .answerId(answer2.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        repo.save(comment1);
        repo.save(comment2);
        repo.save(comment3);

        ArrayList<Comment> comments = (ArrayList) repo.findByAnswer(
                CommentQuery.builder().idAnswer(answer.getId()).build()
        );

        assertEquals(2,comments.size());
    }

    @Test
    public void findByQuestionReturnAllMatchingComment(){
        Comment comment1 = Comment.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        Comment comment2 = Comment.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        Comment comment3 = Comment.builder()
                .questionId(question2.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        repo.save(comment1);
        repo.save(comment2);
        repo.save(comment3);

        ArrayList<Comment> comments = (ArrayList) repo.findByQuestion(
                CommentQuery.builder().idQuestion(question.getId()).build()
        );

        assertEquals(2,comments.size());
    }

    @Test
    public void updateShouldUpdateTheEntryInBDD(){
        Comment expected = Comment.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        repo.save(expected);
        expected.setText("well done");
        repo.update(expected);

        Comment comment = repo.findById(expected.getId()).orElse(null);
        assertTrue(expected.equals(comment));
    }

    @Test
    public void removeShoulddeleteTheEntryInBDD() {
        Comment expected = Comment.builder()
                .questionId(question.getId())
                .personId(author.getId())
                .text("you know nothing")
                .author(author.getUsername())
                .build();

        repo.save(expected);
        expected.setText("well done");
        repo.remove(expected.getId());

        Comment comment = repo.findById(expected.getId()).orElse(null);
        assertNull(comment);
    }
}
