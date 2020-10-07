package stackoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcUserRepository")
public class JdbcUserRepository {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcUserRepository(){

    }

    public JdbcUserRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

}
