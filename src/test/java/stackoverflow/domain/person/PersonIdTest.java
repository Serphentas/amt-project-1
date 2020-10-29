package stackoverflow.domain.person;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonIdTest {
    @Test
    void iCanUsePersonId(){
        PersonId id = new PersonId();
        Person person = Person.builder()
            .id(id)
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alic")
            .lastName("Wonderland")
            .clearTextPassword("abcd")
            .build();

        assertEquals(id, person.getId());
    }

    @Test
    void nullPassedToConstructor(){
        try {
            PersonId id1 = new PersonId((String) null);
            fail();
        } catch (Exception e){}

        try {
            PersonId id1 = new PersonId((UUID) null);
            fail();
        } catch (Exception e){}
    }

    @Test
    void invalidStringPassedToConstructor(){
        try {
            PersonId id = new PersonId("invalid");
            fail();
        } catch (Exception e){}
    }

    @Test
    void iCanTurnInString(){
        PersonId id = new PersonId();
        PersonId id2 = new PersonId(id.asString());

        assertEquals(id.asString(), id2.asString());
    }
}
