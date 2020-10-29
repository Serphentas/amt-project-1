package stackoverflow.domain.vote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.PersonId;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VoteTest {
    private Vote vote;

    @BeforeEach
    void setupVote(){
        vote = Vote.builder()
                .personId(new PersonId("db63a5fe-15f1-11eb-adc1-0242ac120002"))
                .build();
    }

    @Test
    void deepCloneShouldGiveACopy(){
        Vote VoteCopy = vote.deepClone();
        VoteCopy.setPersonId(new PersonId("db6365fe-15f1-11ab-adc1-0242ac120002"));

        assertNotEquals(VoteCopy,vote);
    }
}
