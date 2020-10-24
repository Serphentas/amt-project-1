package stackoverflow.application.identitymngmt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.login.RegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;
import stackoverflow.infrastructure.persistence.jdbc.JdbcUserRepository;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;

import static org.junit.jupiter.api.Assertions.*;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;


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
    public void setup1Register(){
        identityMngmtFacade = new IdentityMngmtFacade(new MemoryPersonRepo());
            RegisterCmd cmd = RegisterCmd.builder()
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
    public void iCanRegister() {
        RegisterCmd cmd = RegisterCmd.builder()
                    .username("Rabbit2")
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
        setup1Register();

        boolean hasException = false;
        RegisterCmd cmd = RegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();

        try {

            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void usernameIsMandatoryInRegister(){
        boolean hasException = false;
        RegisterCmd cmd = RegisterCmd.builder()
            .email("alice.wonderland@gmail.com")
            .firstName("alice")
            .lastName("Wonderland")
            .clearTextPassword("Pa$$w0rd")
            .confirmPassword("Pa$$w0rd")
            .build();
        try {
            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void emailIsMandatoryInRegister(){
        boolean hasException = false;
        RegisterCmd cmd = RegisterCmd.builder()
                .username("Rabbit")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();
        try {
            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void firstNameIsMandatoryInRegister(){
        boolean hasException = false;
        RegisterCmd cmd = RegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .lastName("Wonderland")
                .clearTextPassword("Pa$$w0rd")
                .build();
        try {
            identityMngmtFacade.register(cmd);
            fail();
        } catch (RegistrationFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void lastNameIsMandatoryInRegister(){
        boolean hasException = false;
        RegisterCmd cmd = RegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .clearTextPassword("Pa$$w0rd")
                .build();
        try {
            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void passwordIsMandatoryInRegister(){
        boolean hasException = false;
        RegisterCmd cmd = RegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .build();
        try {
            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void iCanAuthenticate(){
        setup1Register();
        AuthenticateCmd cmd = AuthenticateCmd.builder()
                .username("Rabbit")
                .clearTextPassword("Pa$$w0rd")
                .build();
        try {
            identityMngmtFacade.authenticate(cmd);
        } catch (AuthenticateFailedException e){
            fail();
        }
    }

    @Test
    void usernameIsMandatoryInAuthenticate(){
        setup1Register();
        boolean hasException = false;
        AuthenticateCmd cmd = AuthenticateCmd.builder()
                .clearTextPassword("Pa$$w0rd")
                .build();
        try {
            identityMngmtFacade.authenticate(cmd);
        } catch (AuthenticateFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }

    @Test
    void credentialsShouldBeCorrectToAuthenticate(){
        setup1Register();
        boolean hasException = false;
        AuthenticateCmd cmd = AuthenticateCmd.builder()
                .username("Rabbit")
                .clearTextPassword("blat")
                .build();
        try {
            identityMngmtFacade.authenticate(cmd);
        } catch (AuthenticateFailedException e){
            hasException = true;
        }

        assertTrue(hasException);
    }
}
