package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.answer.IAnswerRepo;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

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
        return null;
    }

    @Override
    public void save(Answer entity) {

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
