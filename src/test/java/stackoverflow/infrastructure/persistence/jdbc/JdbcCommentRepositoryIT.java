package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcCommentRepositoryIT {
    static JdbcCommentRepository repo;

    @BeforeAll
    public static void setupRepoAndBDD() throws SQLException {
        DataSource ds = DataSourceProvider.getDataSource();
        ds.getConnection().prepareStatement("DELETE FROM Comment").execute();
        repo = new JdbcCommentRepository(ds);
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM Comment").execute();
    }
}
