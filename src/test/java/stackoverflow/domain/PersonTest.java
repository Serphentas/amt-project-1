package stackoverflow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import stackoverflow.domain.person.Person;

class PersonTest {

    private Person person;

    @BeforeEach
    void setupPerson(){
        person = Person.builder()
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alic")
            .lastName("Wonderland")
            .clearTextPassword("abcd")
            .build();
    }

    @Test
    void authenticateShouldReturnTrueIfCorrectPasswordFalseOtherwise(){
        assertTrue(person.authenticate("abcd"));
        assertFalse(person.authenticate("abcde"));
    }

    @Test
    void deepCloneShouldGiveACopy(){
        Person personCopy = person.deepClone();
        personCopy.setUsername("RabbitFriend");

        assertNotEquals(personCopy,person);
    }
}
