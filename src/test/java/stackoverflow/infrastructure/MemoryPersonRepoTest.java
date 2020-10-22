package stackoverflow.infrastructure;

import stackoverflow.domain.person.Person;
import stackoverflow.infrastructure.persistence.exception.IntegrityConstraintViolationException;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.fail;

class MemoryPersonRepoTest {

    MemoryPersonRepo memoryPersonRepo;

    @BeforeEach
    void setupEmptyRepo() { memoryPersonRepo = new MemoryPersonRepo(); }

    @Test
    void shouldRespectUniqueUsernameConstraint() {
        Person.PersonBuilder personBuilder = Person.builder()
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alic")
            .lastName("Wonderland")
            .clearTextPassword("abcd");

        memoryPersonRepo.save(personBuilder.build());
        try{
            memoryPersonRepo.save(personBuilder.build());
            fail();
        }
        catch (IntegrityConstraintViolationException e){

        }
    }

    @Test
    void shouldRespectUniqueUsernameConstraintUnderConcurrence() {
        Person.PersonBuilder personBuilder = Person.builder()
                .username("Rabbit")
                .email("alice.wonderland@gmail.com")
                .firstName("alic")
                .lastName("Wonderland")
                .clearTextPassword("abcd");

        memoryPersonRepo.save(personBuilder.build());
        try{
            memoryPersonRepo.save(personBuilder.build());
            fail();
        }
        catch (IntegrityConstraintViolationException e){

        }
    }
}
