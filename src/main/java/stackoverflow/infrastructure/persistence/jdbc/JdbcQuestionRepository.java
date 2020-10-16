package stackoverflow.infrastructure.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(QuestionId id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Question> findAll() {
        Collection<Question> allQuestions = new ArrayList<>();




        return allQuestions;
    }

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        // TODO Auto-generated method stub
        return null;
    }
}
