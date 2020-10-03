package stackoverflow.application.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.login.RegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.person.Person;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;

import static org.junit.jupiter.api.Assertions.*;

public class IdentityMngmtFacadeIT {

    private IdentityMngmtFacade identityMngmtFacade;

    @BeforeEach
    void setupIdentityMngmtFacade(){
        IPersonRepo personRepo = new MemoryPersonRepo();
        this.identityMngmtFacade = new IdentityMngmtFacade(personRepo);
    }

    void setup1Register(){
        RegisterCmd cmd = RegisterCmd.builder()
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alice")
            .lastName("Wonderland")
            .clearTextPassword("abcd")
            .build();
        try {
            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e) {}
    }

    @Test
    void iCanRegister(){
        RegisterCmd cmd = RegisterCmd.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alice")
                .lastName("Wonderland")
                .clearTextPassword("abcd")
                .build();
        try {
            identityMngmtFacade.register(cmd);
        } catch (RegistrationFailedException e){
            fail();
        }
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
                .clearTextPassword("abcd")
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
            .clearTextPassword("abcd")
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
                .clearTextPassword("abcd")
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
                .clearTextPassword("abcd")
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
                .clearTextPassword("abcd")
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
                .clearTextPassword("abcd")
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
                .clearTextPassword("abcd")
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
