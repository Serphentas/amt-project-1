package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.tag.Tag;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;


public class JdbcTagRepositoryIT {
    static JdbcTagRepository repo;

    @BeforeAll
    public static void setupRepoAndBDD() throws SQLException {
        DataSource ds = DataSourceProvider.getDataSource();
        repo = new JdbcTagRepository(ds);
    }

    @Test
    public void findAllShouldReturnAllTag() {
        ArrayList<Tag> tags = (ArrayList<Tag>) repo.findAll();
        assertEquals("C", tags.get(0).getTag());
        assertEquals( "C++", tags.get(1).getTag());
        assertEquals( "Java", tags.get(2).getTag());
        assertEquals( "JavaScript", tags.get(3).getTag());
        assertEquals( "Python", tags.get(4).getTag());
        assertEquals( "SCALA", tags.get(5).getTag());
    }
}
