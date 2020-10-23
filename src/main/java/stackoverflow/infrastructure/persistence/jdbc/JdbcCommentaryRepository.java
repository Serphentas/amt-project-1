package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcCommentaryRepository")
public class JdbcCommentaryRepository implements ICommentRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcCommentaryRepository(){

    }

    public JdbcCommentaryRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void save(Comment entity) {
        if(entity.getQuestionId != null){
            saveComToQuestion(entity);
        } else {
            saveComToAnswer(entity);
        }
    }

    @Override
    public void remove(CommentId id) {

    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        ArrayList<Comment> comments = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT Commentary.idCommentary, Commentary.idUser, Commentary.idQuestion, Commentary.idAnswer, Commentary.text, User.username " +
                            "FROM codemad.Commentary JOIN codemad.User ON User.idUser = Commentary.idUser WHERE idCommentary = ?");
            statement.setString(1, id.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Comment comment = Comment.builder()
                        .id(new CommentId(rs.getString("idCommentary")))
                        .personId(new PersonId(rs.getString("idUser")))
                        .questionId(new QuestionId(rs.getString("idQuestion")))
                        .answerId(new AnswerId(rs.getString("idAnswer")))
                        .text(rs.getString("text"))
                        .author(rs.getString("username"))
                        .build();
                comments.add(comment);
            }

            if(comments.isEmpty()){
                return Optional.empty();
            } else {
                return Optional.of(comments.get(0));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Collection<Comment> findAll() {
        return null;
    }

    private void saveComToQuestion(Comment comment){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO codemad.Commentary (idCommentary, idUser, idQuestion, text) VALUES (?, ?, ?, ?)");
            statement.setString(1, comment.getCommentId());
            statement.setString(2, comment.getUserId());
            statement.setString(3, comment.getQuestionId());
            statement.setString(4, comment.getText());

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void saveComToAnswer(Comment comment) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO codemad.Commentary (idCommentary, idUser, idAnswer, text) VALUES (?, ?, ?, ?)");
            statement.setString(1, comment.getCommentId());
            statement.setString(2, comment.getUserId());
            statement.setString(3, comment.getAnswerId());
            statement.setString(4, comment.getText());

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Collection<Comment> allCommentToQuestion(QuestionId questionId){
        Collection<Comment> allComments = new ArrayList<Comment>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT Commentary.idCommentary, Commentary.idUser, Commentary.idQuestion, Commentary.text, User.username " +
                            "FROM codemad.Commentary JOIN codemad.User ON User.idUser = Commentary.idUser WHERE idQuestion = ?");
            statement.setString(1, questionId.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Comment comment = Comment.builder()
                        .id(new CommentId(rs.getString("idCommentary")))
                        .personId(new PersonId(rs.getString("idUser")))
                        .questionId(new QuestionId(rs.getString("idQuestion")))
                        .text(rs.getString("text"))
                        .author(rs.getString("username"))
                        .build();
                allComments.add(comment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allComments;
    }

    public Collection<Comment> allCommentToAnswer(AnswerId answerId){
        Collection<Comment> allComments = new ArrayList<Comment>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT Commentary.idCommentary, Commentary.idUser, Commentary.idAnswer, Commentary.text, User.username " +
                            "FROM codemad.Commentary JOIN codemad.User ON User.idUser = Commentary.idUser WHERE idQuestion = ?");
            statement.setString(1, questionId.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Comment comment = Comment.builder()
                        .id(new CommentId(rs.getString("idCommentary")))
                        .personId(new PersonId(rs.getString("idUser")))
                        .answerId(new QuestionId(rs.getString("idAnswer")))
                        .text(rs.getString("text"))
                        .author(rs.getString("username"))
                        .build();
                allComments.add(comment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allComments;
    }
}
