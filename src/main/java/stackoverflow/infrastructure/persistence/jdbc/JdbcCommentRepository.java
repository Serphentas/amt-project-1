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
@Named("JdbcCommentRepository")
public class JdbcCommentRepository implements ICommentRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcCommentRepository() {
    }

    public JdbcCommentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Comment entity) {
        try {
            PreparedStatement statement = getStatement(String.format(
                "INSERT INTO codemad.Comment (idComment, idUser, %s, text) VALUES (?, ?, ?, ?)",
                entity.getQuestionId() != null ? "idQuestion" : "idAnswer"
            ));
            statement.setString(1, new CommentId().asString());
            statement.setString(2, entity.getPersonId().asString());
            statement.setString(3, (entity.getQuestionId() != null ?
                    entity.getQuestionId(): entity.getAnswerId()
                ).asString()
            );
            statement.setString(4, entity.getText());
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
                "SELECT Comment.idComment, Comment.idUser, Comment.idQuestion, Comment.idAnswer, Comment.text, User.username " +
                "FROM codemad.Comment JOIN codemad.User ON User.idUser = Comment.idUser WHERE idComment = ?");
            statement.setString(1, id.asString());

            ArrayList<Comment> comments = new ArrayList<>();
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                comments.add(Comment.builder()
                    .id(new CommentId(rs.getString("idComment")))
                    .personId(new PersonId(rs.getString("idUser")))
                    .answerId(new AnswerId(rs.getString("idAnswer")))
                    .questionId(new QuestionId(rs.getString("idQuestion")))
                    .author(rs.getString("author"))
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
    public void update(Comment entity) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "UPDATE codemad.Comment SET text=? WHERE idComment=?");
            statement.setString(1, entity.getText());
            statement.setString(2, entity.getId().asString());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Collection<Comment> findByQuestion(CommentQuery query) {
        try {
            PreparedStatement statement = getStatement(
                "SELECT Comment.idComment, Comment.idUser, Comment.idQuestion, Comment.text, User.username " +
                "FROM codemad.Comment JOIN codemad.User ON User.idUser = Comment.idUser WHERE idQuestion = ?"
            );
            statement.setString(1, query.getIdQuestion().toString());

            return resultSetAsList(statement.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Comment>();
    }

    @Override
    public Collection<Comment> findByAnswer(CommentQuery query) {
        try {
            PreparedStatement statement = getStatement(
                "SELECT Comment.idComment, Comment.idUser, Comment.idAnswer, Comment.text, User.username " +
                "FROM codemad.Comment JOIN codemad.User ON User.idUser = Comment.idUser WHERE idAnswer = ?"
            );
            statement.setString(1, query.getIdAnswer().toString());

            return resultSetAsList(statement.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Comment>();
    }

    private PreparedStatement getStatement (String cmd) throws SQLException {
        return dataSource.getConnection().prepareStatement(cmd);
    }

    private ArrayList<Comment> resultSetAsList(ResultSet rs) throws SQLException {
        ArrayList<Comment> res = new ArrayList<>();

        while (rs.next()) {
            res.add(Comment.builder()
                .id(new CommentId(rs.getString("idComment")))
                .answerId(new AnswerId(rs.getString("idAnswer")))
                .questionId(new QuestionId(rs.getString("idQuestion")))
                .personId(new PersonId(rs.getString("idUser")))
                .author(rs.getString("author"))
                .text(rs.getString("text"))
                .build()
            );
        }

        return res;
    }
}
