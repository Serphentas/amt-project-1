package stackoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcCommentaryRepository")
public class JdbcCommentRepository {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcCommentRepository(){

    }

    public JdbcCommentRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
