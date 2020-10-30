package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JdbcUserRepositoryIT {
    static JdbcUserRepository repo;

    @BeforeAll
    public static void setupRepository() throws IOException, SQLException {
        DataSource ds = DataSourceProvider.getDataSource();
        ds.getConnection().prepareStatement("DELETE FROM User").execute();
        repo = new JdbcUserRepository(ds);
    }

    @AfterAll
    public static void cleanBDD() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM User").execute();
    }

    @Test
    public void findByUsernameReturnNullWhenNoPersonIsFound(){
        Person nonExistingPerson = repo.findByUsername("MrInvisible").orElse(null);
        assertNull(nonExistingPerson);
    }

    @Test
    public void saveShouldPutAnEntryInDBB(){
        repo.save( Person.builder()
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alice")
            .lastName("Wonderland")
            .clearTextPassword("Pa$$w0rd")
            .build()
        );
        assertNotEquals(null, repo.findByUsername("Rabbit").orElse(null));
    }
}
