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
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.tag.Tag;
import stackoverflow.domain.tag.TagId;

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
            statement.setString(2, entity.getUserId().asString());
            statement.setString(3, entity.getTitle());
            statement.setString(4, entity.getText());
            statement.execute();

            if( entity.getTags() != null) {
                for (Tag tag : entity.getTags()) {
                    statement = dataSource.getConnection().prepareStatement(
                            "INSERT INTO codemad.Question_Tag (idQuestion, idTag) VALUES (?, ?)");
                    statement.setString(1, entity.getId().asString());
                    statement.setString(2, tag.getId().asString());
                    statement.execute();
                }
            }
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
        ArrayList<Question> question = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT Question.idQuestion, Question.idUser, Question.title, Question.text, User.username " +
                            "FROM codemad.Question JOIN codemad.User ON Question.idUser = User.idUser WHERE Question.idQuestion = ?");
            statement.setString(1, id.asString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Collection<Tag> tags = searchTags(rs.getString("idQuestion"));

                Question questionSearch = Question.builder()
                        .id(new QuestionId(rs.getString("idQuestion")))
                        .author(rs.getString("username"))
                        .title(rs.getString("title"))
                        .text(rs.getString("text"))
                        .userId(new PersonId(rs.getString("idUser")))
                        .tags(tags)
                        .build();
                question.add(questionSearch);
            }
            if(question.isEmpty()){
                return Optional.empty();
            } else {
                return Optional.of(question.get(0));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        Collection<Question> allQuestions = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT Question.idQuestion, Question.idUser, Question.title, Question.text, User.username " +
                            "FROM codemad.Question JOIN codemad.User ON Question.idUser = User.idUser");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Collection<Tag> tags = searchTags(rs.getString("idQuestion"));

                Question question = Question.builder()
                        .id(new QuestionId(rs.getString("idQuestion")))
                        .author(rs.getString("username"))
                        .title(rs.getString("title"))
                        .text(rs.getString("text"))
                        .userId(new PersonId(rs.getString("idUser")))
                        .tags(tags)
                        .build();
                allQuestions.add(question);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allQuestions;
    }

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        /*try {
            PreparedStatement statement = getStatement(query.isSafeForChildren() ?
                "SELECT * FROM codemad.Question WHERE title LIKE '%?%' AND text NOT LIKE '%?%'" :
                "SELECT * FROM codemad.Question WHERE title LIKE '%?%' AND text LIKE '%?%'"
            );
            statement.setString(1, query.getTitle());
            statement.setString(2, query.isSafeForChildren() ? "sex" : query.getText());

            return resultSetAsList(statement.executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
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

    private Collection<Tag> searchTags(String questionId) throws SQLException {
        Collection<Tag> tags = new ArrayList<Tag>();

        PreparedStatement statementTag = dataSource.getConnection().prepareStatement(
                "SELECT Tag.idTag, Tag.tag FROM codemad.Tag JOIN codemad.Question_Tag ON Question_Tag.idTag = Tag.idTag " +
                    "WHERE Question_Tag.idQuestion = ?");
        statementTag.setString(1, questionId);

        ResultSet rsTag = statementTag.executeQuery();

        while (rsTag.next()){
            Tag tag = Tag.builder()
                    .tagId(new TagId(rsTag.getString("idTag")))
                    .tag(rsTag.getString("tag"))
                    .build();
            tags.add(tag);
        }
        return tags;

    }
}
