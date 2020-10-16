package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.answer.IAnswerRepo;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

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
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository implements IAnswerRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcAnswerRepository(){

    }

    public JdbcAnswerRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Collection<Answer> find(AnswersQuery query) {
        Collection<Answer> allAnswer = new ArrayList<Answer>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT Answer.text, Answer.idAnswer, Answer.idQuestion, User.username, Answer.idUser" +
                            "FROM codemad.Answer JOIN User ON Answer.idUser = User.idUser WHERE idQuestion=?");
            statement.setString(1, query.getId().toString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Answer answer = Answer.builder()
                        .author(rs.getString("username"))
                        .id(new AnswerId(rs.getString("idAnswer")))
                        .questionId(new QuestionId(rs.getString("idQuestion")))
                        .personId(new PersonId(rs.getString("idUser")))
                        .text(rs.getString("text"))
                        .build();
                allAnswer.add(answer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allAnswer;
    }

    @Override
    public void save(Answer entity) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO codemad.Answer (idAnswer, idUser, idQuestion, text) VALUES (?, ?, ?, ?)");
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getPersonId().asString());
            statement.setString(3, entity.getQuestionId().asString());
            statement.setString(4, entity.getText());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(AnswerId id) {

    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        return Optional.empty();

    }

    @Override
    public Collection<Answer> findAll() {
        return null;
    }

}
