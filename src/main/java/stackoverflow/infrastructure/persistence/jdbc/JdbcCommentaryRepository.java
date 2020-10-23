package stackoverflow.infrastructure.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import stackoverflow.application.comment.CommentQuery;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.comment.Comment;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.comment.ICommentRepo;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

@ApplicationScoped
@Named("JdbcCommentaryRepository")
public class JdbcCommentaryRepository implements ICommentRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcCommentaryRepository() {
    }

    public JdbcCommentaryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Comment entity) {
        try {
            PreparedStatement statement = getStatement(
                    "INSERT INTO codemad.Comment (idComment, idUser, " +
                    "idAnswer, idQuestion, text) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, new CommentId().asString());
            statement.setString(2, entity.getIdUser().asString());
            statement.setString(3, entity.getIdAnswer().asString());
            statement.setString(4, entity.getIdQuestion().asString());
            statement.setString(5, entity.getText());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(CommentId id) {
        try {
            PreparedStatement statement = getStatement(
                    "DELETE FROM codemad.Comment WHERE idComment=?");
            statement.setString(1, id.asString());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        try {
            PreparedStatement statement = getStatement(
                    "SELECT * codemad.Comment WHERE idComment=?");
            statement.setString(1, id.asString());

            ArrayList<Comment> comments = new ArrayList<>();
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                comments.add(Comment.builder()
                    .id(new CommentId(rs.getString("idComment")))
                    .idUser(new PersonId(rs.getString("idUser")))
                    .idAnswer(new AnswerId(rs.getString("idAnswer")))
                    .idQuestion(new QuestionId(rs.getString("idQuestion")))
                    .text(rs.getString("text"))
                    .build()
                );
            }

            if(comments.isEmpty()){
                return Optional.empty();
            }
            return Optional.of(comments.get(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Comment> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Comment> findByQuestion(CommentQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Comment> findByAnswer(CommentQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

    private PreparedStatement getStatement (String cmd) throws SQLException {
        return dataSource.getConnection().prepareStatement(cmd);
    }
}
