package stackoverflow.application.identitymngmt;

import stackoverflow.application.identitymngmt.authenticate.ProposeAuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.identitymngmt.login.ProposeRegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.person.Person;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IdentityMngmtFacade {
    private IPersonRepo personRepo;

    public IdentityMngmtFacade(IPersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    private boolean isStrongPassword(String clearTextPassword){
        boolean result = clearTextPassword.matches(".*[0-9].*");
        result &= clearTextPassword.matches(".*[A-Z].*");
        result &= clearTextPassword.matches(".*[a-z].*");
        result &= clearTextPassword.matches(".*^[a-z|A-Z|0-9].*");

        return result && clearTextPassword.length()>7;
    }

    public void register(ProposeRegisterCmd cmd) throws RegistrationFailedException {
        Person yetExistingPerson = personRepo.findByUsername(cmd.getUsername()).orElse(null);

        if( yetExistingPerson != null){
            throw new RegistrationFailedException("Username is already used");
        }

        if( ! cmd.getConfirmPassword().equals(cmd.getClearTextPassword()) ){
            throw new RegistrationFailedException("confirm password field don't match the password field");
        }

        if( ! isStrongPassword(cmd.getClearTextPassword()) ){
            throw new RegistrationFailedException("password should respect the following rules:\n" +
                " at least a majuscule\n" +
                " at least a miniscule\n" +
                " at least a digit\n" +
                " at least a special character\n" +
                " have minimum 8 characters");
        }

        try {
            Person newPerson = Person.builder()
                .username(cmd.getUsername())
                .email(cmd.getEmail())
                .firstName(cmd.getFirstName())
                .lastName(cmd.getLastName())
                .clearTextPassword(cmd.getClearTextPassword())
                .build();
            personRepo.save(newPerson);

        } catch (Exception e) {
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO authenticate(ProposeAuthenticateCmd cmd) throws AuthenticateFailedException {
        Person person = personRepo.findByUsername(cmd.getUsername())
            .orElseThrow(() -> new AuthenticateFailedException("User not found"));

        boolean success = person.authenticate(cmd.getClearTextPassword());
        if(!success){
            throw new AuthenticateFailedException("Verification of credentials failed");
        }

        return CurrentUserDTO.builder()
            .id(person.getId())
            .username(person.getUsername())
            .email(person.getEmail())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .build();
    }

    public void updateProfil(UpdateProfilCmd cmd, CurrentUserDTO currentUser){
        if(cmd.getFirstName().isEmpty() || cmd.getFirstName() == null){
            cmd.setFirstName(currentUser.getFirstName());
        }

        if(cmd.getLastName().isEmpty() || cmd.getLastName() == null){
            cmd.setLastName(currentUser.getLastName());
        }

        if(cmd.getEmail().isEmpty() || cmd.getEmail() == null){
            cmd.setEmail(currentUser.getEmail());
        }

        Person updatePerson = Person.builder()
                .id(cmd.getPersonId())
                .username(cmd.getUsername())
                .firstName(cmd.getFirstName())
                .lastName(cmd.getLastName())
                .email(cmd.getEmail())
                .encryptedPassword("nothing")
                .build();
        personRepo.update(updatePerson);
    }
}
