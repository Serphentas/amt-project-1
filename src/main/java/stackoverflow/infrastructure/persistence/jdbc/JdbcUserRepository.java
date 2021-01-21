package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.ConnectionAPI;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

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

    @Resource(lookup = "gamification/events")
    String gamificationEventURL;

    @Resource(lookup = "gamification/apikey")
    String gamificationKey;

    public JdbcUserRepository() {}

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Person person) {
        try {
            String userId = new PersonId().asString();

            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO codemad.User (idUser, username, firstname, lastname, email, password) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, userId);
            statement.setString(2, person.getUsername());
            statement.setString(3, person.getFirstName());
            statement.setString(4, person.getLastName());
            statement.setString(5, person.getEmail());
            statement.setString(6, person.getEncryptedPassword());

            statement.execute();

            new ConnectionAPI().post("registration", userId, gamificationEventURL, gamificationKey);

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
    public void update(Person entity) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "UPDATE codemad.User SET firstname=?, lastname=?, email=? WHERE idUser=?");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getId().asString());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                "SELECT * FROM codemad.User WHERE username=?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            ArrayList<Person> rows = new ArrayList<>();
            while (rs.next()) {
                Person person = Person.builder()
                    .id(new PersonId(rs.getString("idUser")))
                    .username(rs.getString("username"))
                    .encryptedPassword(rs.getString("password"))
                    .email(rs.getString("email"))
                    .firstName(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
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

    @Override
    public Optional<Integer> countAll() {
        ArrayList<Integer> count = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(*) AS nbr FROM User GROUP BY idUser");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                count.add(rs.getInt("nbr"));
            }

            if(count.isEmpty()){
                return Optional.empty();
            } else {
                return Optional.of(count.get(0));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }
}
