package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.IVote;
import stackoverflow.domain.vote.Vote;
import stackoverflow.domain.vote.VoteId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Named("JdbcVoteRepository")
public class JdbcVoteRepository implements IVote {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcVoteRepository(){

    }

    public JdbcVoteRepository(DataSource dataSource){
        this.dataSource = dataSource;
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

    public void voteForQuestion(QuestionId questionId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO `codemad`.`Vote`(idVote, idUser, idQuestion) VALUES (?, ?, ?)");
            statement.setString(1, UUID.randomUUID().toString());
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


    /*
    public void voteForCommentary(CommentaryId commentaryId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO `codemad`.`Vote`(idVote, idUser, idCommentary) VALUES (?, ?, ?)");
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, personId.asString());
            statement.setString(3, commentaryId.asString());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean hasVotedCommentary(){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM codemad.Vote WHERE idUser=? AND idCommentary=?");
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

    public int nbrVoteCommentary(CommentaryId commentaryId){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(Vote.idVote) AS nbrVote FROM codemad.Vote GROUP BY Vote.idCommentary HAVING idCommentary=?");
            statement.setString(1, commentaryId.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                return rs.getInt("nbrVote");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void unvoteForCommentary(CommentaryId commentaryId, PersonId personId){
        try {

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "DELETE FROM `codemad`.`Vote` WHERE idUser=? AND idCommentary=?");
            statement.setString(1, personId.asString());
            statement.setString(2, questionId.asString());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    */
}
