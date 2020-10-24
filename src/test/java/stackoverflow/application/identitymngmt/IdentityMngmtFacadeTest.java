package stackoverflow.application.identitymngmt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stackoverflow.application.identitymngmt.authenticate.ProposeAuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.login.ProposeRegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;

import static org.junit.jupiter.api.Assertions.*;


public class IdentityMngmtFacadeTest {
/*
    static SeContainerInitializer initializer;

    @BeforeAll
    public static void setupCdi() {
        initializer = SeContainerInitializer.newInstance();
    }

    //todo comment utiliser cette stratégie avec un datasource?
    */

    private IdentityMngmtFacade identityMngmtFacade;

    @BeforeEach
    public void setupIdentityMngmtFacade(){
        identityMngmtFacade = new IdentityMngmtFacade(new MemoryPersonRepo());
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
            identityMngmtFacade.register(cmd);
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
            identityMngmtFacade.register(cmd);
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
            identityMngmtFacade.register(cmd);
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
            identityMngmtFacade.authenticate(cmd);
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
            identityMngmtFacade.authenticate(cmd);
        });
    }
}
