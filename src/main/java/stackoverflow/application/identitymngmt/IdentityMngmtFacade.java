package stackoverflow.application.identitymngmt;

import stackoverflow.application.identitymngmt.authenticate.AuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.identitymngmt.login.RegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.person.Person;
import stackoverflow.infrastructure.persistence.jdbc.JdbcUserRepository;

public class IdentityMngmtFacade {
    private IPersonRepo personRepo;

    public IdentityMngmtFacade(IPersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public void register(RegisterCmd cmd) throws RegistrationFailedException {
        Person yetExistingPerson = personRepo.findByUsername(cmd.getUsername()).orElse(null);

        if( yetExistingPerson != null){
            throw new RegistrationFailedException("Username is already used");
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

    public CurrentUserDTO authenticate(AuthenticateCmd cmd) throws AuthenticateFailedException {
        Person person = personRepo.findByUsername(cmd.getUsername())
            .orElseThrow(() -> new AuthenticateFailedException("User not found"));

        boolean success = person.authenticate(cmd.getClearTextPassword());
        if(!success){
            throw new AuthenticateFailedException("Verification of credentials failed");
        }

        return CurrentUserDTO.builder()
            .username(person.getUsername())
            .email(person.getEmail())
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .build();
    }

}
