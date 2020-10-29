package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import static org.junit.jupiter.api.Assertions.assertNull;

public class JdbcUserRepositoryIT {
    static JdbcUserRepository repo;

    @BeforeAll
    public static void setupRepository() { repo = new JdbcUserRepository(DataSourceProvider.getDataSource()); }

    @Test
    public void findByIdReturnNullWhenNoPersonIsFound(){
        Person nonExistingPerson = repo.findById(new PersonId()).orElse(null);
        assertNull(nonExistingPerson);
    }
}
