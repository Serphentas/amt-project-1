package stackoverflow.infrastructure.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository implements IQuestionRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcQuestionRepository() {}

    public JdbcQuestionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Question entity) {
        try {
            PreparedStatement statement = getStatement(
                    "INSERT INTO codemad.Question (idQuestion, idUser, title, text) VALUES (?, ?, ?, ?)");
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, entity.getAuthor());
            statement.setString(3, entity.getTitle());
            statement.setString(4, entity.getText());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(QuestionId id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        try {
            PreparedStatement statement = getStatement("SELECT * FROM codemad.Question WHERE idQuestion=?");
            statement.setString(1, id.asString());

            ArrayList<Question> rows = resultSetAsList(statement.executeQuery());

            if (rows.size() == 0) {
                return Optional.empty();
            }
            return Optional.of(rows.get(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        try {
            return resultSetAsList(getStatement("SELECT * FROM codemad.Question").executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Question>();
    }

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        try {
            PreparedStatement statement = getStatement(String.format(
                "SELECT * FROM codemad.Question WHERE title LIKE '%?%' AND text %sLIKE '%?%'",
                query.isSafeForChildren() ? "NOT " : ""
            ));
            statement.setString(1, query.getTitle());
            statement.setString(2, query.isSafeForChildren() ? "sex" : query.getText());

            return resultSetAsList(statement.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<Question>();
    }

    private ArrayList<Question> resultSetAsList(ResultSet rs) throws SQLException {
        ArrayList<Question> res = new ArrayList<>();

        while (rs.next()) {
            res.add(Question.builder()
                .id(new QuestionId(rs.getString("idQuestion")))
                .author(rs.getString("idUser"))
                .title(rs.getString("title"))
                .text(rs.getString("text"))
                .build()
            );
        }

        return res;
    }

    private PreparedStatement getStatement (String cmd) throws SQLException {
        return dataSource.getConnection().prepareStatement(cmd);
    }
}
