package stackoverflow.domain.person;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.IEntity;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person, PersonId>{

    private PersonId id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String  encryptedPassword;

    public boolean authenticate(String clearTextPassword){
        return clearTextPassword.toUpperCase().equals(encryptedPassword);
    }

    @Override
    public Person deepClone() {
        return this.toBuilder()
            .id(new PersonId(id.asString()))
            .build();
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
