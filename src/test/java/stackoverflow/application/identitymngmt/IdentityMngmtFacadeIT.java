package stackoverflow.application.identitymngmt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.application.identitymngmt.authenticate.ProposeAuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.login.ProposeRegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;
import stackoverflow.infrastructure.persistence.jdbc.JdbcUserRepository;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityMngmtFacadeIT {

    static IdentityMngmtFacade facade;

    @BeforeAll
    public static void setupFacade(){
        facade = new IdentityMngmtFacade(new JdbcUserRepository(DataSourceProvider.getDataSource()));
    }

    @AfterEach
    public void cleanDBB() throws SQLException {
        DataSourceProvider.getDataSource().getConnection().prepareStatement("DELETE FROM User").execute();
    }

    @Test
    public void iCanRegister(){
        ProposeRegisterCmd cmd = ProposeRegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .confirmPassword("Pa$$w0rd")
                .build();
        assertDoesNotThrow(() -> {
            facade.register(cmd);
        });

    }

    @Test
    void registerThrowIfUsernameExist(){
        iCanRegister();
        ProposeRegisterCmd cmd = ProposeRegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .confirmPassword("Pa$$w0rd")
                .build();

        assertThrows(RegistrationFailedException.class, () -> {
            facade.register(cmd);
        });
    }

    @Test
    void registerThrowIfclearTextPasswordNotEqualConfirmPassword(){
        ProposeRegisterCmd cmd = ProposeRegisterCmd.builder()
                .username("Rabbit2")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd1")
                .confirmPassword("Pa$$w0rd2")
                .build();

        assertThrows(RegistrationFailedException.class, () -> {
            facade.register(cmd);
        });
    }

    @Test
    void iCanAuthenticate(){
        iCanRegister();
        ProposeAuthenticateCmd cmd = ProposeAuthenticateCmd.builder()
                .username("Rabbit")
                .clearTextPassword("Pa$$w0rd")
                .build();

        assertDoesNotThrow(() -> {
            facade.authenticate(cmd);
        });
    }

    @Test
    void credentialsShouldBeCorrectToAuthenticate(){
        iCanRegister();
        ProposeAuthenticateCmd cmd = ProposeAuthenticateCmd.builder()
                .username("Rabbit")
                .clearTextPassword("blat")
                .build();

        assertThrows(AuthenticateFailedException.class, () -> {
            facade.authenticate(cmd);
        });
    }
}
