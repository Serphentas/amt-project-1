package stackoverflow.domain.person;

import lombok.*;
import stackoverflow.domain.IEntity;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person, PersonId>{

    @Setter(AccessLevel.NONE)
    private PersonId id = new PersonId();

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean authenticate(String clearTextPassword){
        return clearTextPassword.toUpperCase().equals(encryptedPassword);
    }

    @Override
    public Person deepClone() {
        return this.toBuilder()
            .id(new PersonId(id.asString()))
            .build();
    }

    private static SecureRandom random = new SecureRandom();
    private static String encrypt(String password){
        byte[]salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded().toString();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new FailedHashingPasswordException(e.getMessage());
        }
    }

    public static class PersonBuilder {
        public PersonBuilder clearTextPassword(String clearTextPassword){
            if(clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new IllegalArgumentException("Password is mandatory");
            }

            encryptedPassword = clearTextPassword.toUpperCase();
            return this;
        }

        public Person build(){
            if(id == null){
                id = new PersonId();
            }

            if(username == null || username.isEmpty()){
                throw new IllegalArgumentException("Username is mandatory");
            }

            if(encryptedPassword == null || encryptedPassword.isEmpty()){
                throw new IllegalArgumentException("Password is mandatory");
            }

            if(email == null || email.isEmpty()){
                throw new IllegalArgumentException("Email is mandatory");
            }

            if(firstName == null || firstName.isEmpty()){
                throw new IllegalArgumentException("FirstName is mandatory");
            }

            if(lastName == null || lastName.isEmpty()){
                throw new IllegalArgumentException("LastName is mandatory");
            }

            return new Person(id, username, email, firstName, lastName, encryptedPassword);
        }

    }
}
