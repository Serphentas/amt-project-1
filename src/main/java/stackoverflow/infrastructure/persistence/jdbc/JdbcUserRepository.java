package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.xml.registry.infomodel.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcUserRepository")
public class JdbcUserRepository implements IPersonRepo {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcUserRepository() {}

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Person person) {
        try {
            System.out.println(dataSource == null);
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO `codemad`.`user`(`pseudo`,`name`,`surname`,`email`,`password`) VALUES ?, ?, ?, ?, ?");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getEmail());
            statement.setString(5, person.getEncryptedPassword());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(PersonId id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Person> findById(PersonId id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM `codemad`.`User` WHERE pseudo=?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            ArrayList<Person> rows = new ArrayList<>();
            while (rs.next()) {
                Person person = Person.builder()
                    .id(new PersonId(rs.getString("id")))
                    .username(rs.getString("username"))
                    .encryptedPassword(rs.getString("encryptedPassword"))
                    .email(rs.getString("email"))
                    .firstName(rs.getString("firstName"))
                    .lastName(rs.getString("lastName"))
                    .build();
                rows.add(person);
            }

            if (rows.size() == 0) {
                return Optional.empty();
            }
            return Optional.of(rows.get(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
