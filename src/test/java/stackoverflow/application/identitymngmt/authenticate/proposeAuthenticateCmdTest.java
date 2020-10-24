package stackoverflow.application.identitymngmt.authenticate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class proposeAuthenticateCmdTest {
    @Test
    public void personIdShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeAuthenticateCmd.builder()
                    .clearTextPassword("password")
                    .build();
        });
    }

    @Test
    public void usernameShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeAuthenticateCmd.builder()
                    .username("username")
                    .build();
        });
    }
}
