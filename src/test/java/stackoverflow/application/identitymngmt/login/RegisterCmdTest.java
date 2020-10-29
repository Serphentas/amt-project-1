package stackoverflow.application.identitymngmt.login;

import lombok.NonNull;
import org.junit.jupiter.api.Test;
import stackoverflow.application.question.ProposeQuestionCmd;
import stackoverflow.application.vote.ProposeVoteCmd;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.vote.VoteId;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegisterCmdTest {

    @Test
    public void usernameShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeRegisterCmd.builder()
                    .email("email")
                    .firstName("firstName")
                    .lastName("lastName")
                    .clearTextPassword("password")
                    .confirmPassword("confirmPassword")
                    .build();
        });
    }

    @Test
    public void emailShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeRegisterCmd.builder()
                    .username("username")
                    .firstName("firstName")
                    .lastName("lastName")
                    .clearTextPassword("password")
                    .confirmPassword("confirmPassword")
                    .build();
        });
    }

    @Test
    public void firstNameShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeRegisterCmd.builder()
                    .username("username")
                    .email("email")
                    .lastName("lastName")
                    .clearTextPassword("password")
                    .confirmPassword("confirmPassword")
                    .build();
        });
    }

    @Test
    public void lastNameShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeRegisterCmd.builder()
                    .username("username")
                    .email("email")
                    .firstName("firstName")
                    .clearTextPassword("password")
                    .confirmPassword("confirmPassword")
                    .build();
        });
    }

    @Test
    public void clearTextPasswordShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeRegisterCmd.builder()
                    .username("username")
                    .email("email")
                    .firstName("firstName")
                    .lastName("lastName")
                    .confirmPassword("confirmPassword")
                    .build();
        });
    }

    @Test
    public void confirmPasswordShouldBeMandatory() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            ProposeRegisterCmd.builder()
                    .username("username")
                    .email("email")
                    .firstName("firstName")
                    .lastName("lastName")
                    .clearTextPassword("password")
                    .build();
        });
    }
}
