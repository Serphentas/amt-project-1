package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.domain.person.Person;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ResultSet addUser(Person person){
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO User(pseudo, name, surname, email, password) VALUES ?, ?, ?, ?, ?");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getEmail());
            statement.setString(5, person.getEncryptedPassword());

            return statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
