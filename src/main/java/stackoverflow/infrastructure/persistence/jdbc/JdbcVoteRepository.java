package stackoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcVoteRepository")
public class JdbcVoteRepository {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcVoteRepository(){

    }

    public JdbcVoteRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
