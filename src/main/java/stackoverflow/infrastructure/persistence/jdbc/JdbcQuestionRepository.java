package stackoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcQuestionRepository(){

    }

    public JdbcQuestionRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
