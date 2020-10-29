package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import static org.junit.jupiter.api.Assertions.assertNull;

public class JdbcUserRepositoryIT {
    static JdbcUserRepository repo;

    @BeforeAll
    public static void setupRepository() { repo = new JdbcUserRepository(DataSourceProvider.getDataSource()); }

    @Test
    public void findByUsernameReturnNullWhenNoPersonIsFound(){
        Person nonExistingPerson = repo.findByUsername("Rabbit").orElse(null);
        assertNull(nonExistingPerson);
    }

    @Test
    public void saveShouldWork(){
        repo.save( Person.builder()
            .username("Rabbit2")
            .email("alice.wonderland@gmail.com")
            .firstName("alice")
            .lastName("Wonderland")
            .clearTextPassword("Pa$$w0rd1")
            .build()
        );
    }
}
