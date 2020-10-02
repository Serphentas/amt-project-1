package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.Person;

class PersonTest {
    //authenticate

    @Test
    void deepCloneShouldGiveACopy(){
        Person.PersonBuilder personBuilder = Person.builder()
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alic")
            .lastName("Wonderland")
            .clearTextPassword("abcd");

        Person person = personBuilder.build();
        Person personCopy = person.deepClone();
        personCopy.setUsername("RabbitFriend");

        assertNotEquals(personCopy,person);
    }

    //encrypt
    //build
}
