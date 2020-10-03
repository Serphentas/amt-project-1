package stackoverflow.application.identitymngmt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

public class CurrentUserDTOTest {
    @Test
    void iCanUseDTO(){
        CurrentUserDTO user = CurrentUserDTO.builder()
            .username("Rabbit")
            .email("alice.wonderland@gmail.com")
            .firstName("alice")
            .lastName("Wonderland")
            .build();

        assertEquals("Rabbit", user.getUsername());
        assertEquals("alice.wonderland@gmail.com", user.getEmail());
        assertEquals("alice", user.getFirstName());
        assertEquals("Wonderland", user.getLastName());
    }
}
