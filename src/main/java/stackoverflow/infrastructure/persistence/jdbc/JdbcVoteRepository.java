package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.IVoteRepo;
import stackoverflow.domain.vote.Vote;
import stackoverflow.domain.vote.VoteId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Named("JdbcVoteRepository")
public class JdbcVoteRepository implements IVoteRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcVoteRepository(){

    }

    public JdbcVoteRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void toggle(Vote entity) {
        if (entity.getQuestionId() != null) {
            if (!hasVotedQuestion(entity.getQuestionId(), entity.getPersonId())) {
                voteForQuestion(entity.getQuestionId(), entity.getPersonId());
            } else {
                unvoteForQuestion(entity.getQuestionId(), entity.getPersonId());

            }
        } else if (entity.getCommentId() != null){
            if (!hasVotedComment(entity.getCommentId(), entity.getPersonId())) {
                voteForComment(entity.getCommentId(), entity.getPersonId());
            } else {
                unvoteForComment(entity.getCommentId(), entity.getPersonId());
            }
        }
    }

    @Override
    public void save(Vote entity) {

    }

    @Override
    public void remove(VoteId id) {

    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        return null;
    }

    @Override
    public void update(Vote entity) {

    }


    private void voteForQuestion(QuestionId questionId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO `codemad`.`Vote`(idVote, idUser, idQuestion) VALUES (?, ?, ?)");
            statement.setString(1, new VoteId().asString());
            statement.setString(2, personId.asString());
            statement.setString(3, questionId.asString());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean hasVotedQuestion(QuestionId questionId, PersonId currentUser){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM codemad.Vote WHERE idUser=? AND idQuestion=?");
            statement.setString(1, currentUser.asString());
            statement.setString(2, questionId.asString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public int nbrVoteQuestion(QuestionId questionId){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(Vote.idVote) AS nbrVote FROM codemad.Vote GROUP BY Vote.idQuestion HAVING idQuestion=?");
            statement.setString(1, questionId.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                return rs.getInt("nbrVote");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void unvoteForQuestion(QuestionId questionId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "DELETE FROM `codemad`.`Vote` WHERE idUser=? AND idQuestion=?");
            statement.setString(1, personId.asString());
            statement.setString(2, questionId.asString());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void voteForComment(CommentId commentId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO `codemad`.`Vote`(idVote, idUser, idComment) VALUES (?, ?, ?)");
            statement.setString(1, new VoteId().asString());
            statement.setString(2, personId.asString());
            statement.setString(3, commentId.asString());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean hasVotedComment(CommentId commentId, PersonId currentUser){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM codemad.Vote WHERE idUser=? AND idComment=?");
            statement.setString(1, currentUser.asString());
            statement.setString(2, commentId.asString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public int nbrVoteComment(CommentId commentId){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(Vote.idVote) AS nbrVote FROM codemad.Vote GROUP BY Vote.idComment HAVING idComment=?");
            statement.setString(1, commentId.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                return rs.getInt("nbrVote");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void unvoteForComment(CommentId commentId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "DELETE FROM `codemad`.`Vote` WHERE idUser=? AND idComment=?");
            statement.setString(1, personId.asString());
            statement.setString(2, commentId.asString());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Integer> countAll() {
        ArrayList<Integer> count = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(*) AS nbr FROM Vote GROUP BY idVote");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                count.add(rs.getInt("nbr"));
            }

            if(count.isEmpty()){
                return Optional.empty();
            } else {
                return Optional.of(count.get(0));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Integer> countAllOfUser(PersonId id) {
        ArrayList<Integer> count = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(*) AS nbr FROM Vote WHERE idUser=?");
            statement.setString(1, id.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("nbr"));
            }

            if(count.isEmpty()){
                return Optional.empty();
            } else {
                return Optional.of(count.get(0));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }
}
