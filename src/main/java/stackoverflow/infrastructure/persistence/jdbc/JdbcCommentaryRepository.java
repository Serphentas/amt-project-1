package stackoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcCommentaryRepository")
public class JdbcCommentaryRepository {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcCommentaryRepository(){

    }

    public JdbcCommentaryRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
