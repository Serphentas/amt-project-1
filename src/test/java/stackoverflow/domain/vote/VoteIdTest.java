package stackoverflow.domain.vote;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.person.PersonId;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class VoteIdTest {
    @Test
    void iCanUseVoteId(){
        VoteId id = new VoteId();
        Vote vote = Vote.builder()
                .voteId(id)
                .personId(new PersonId())
                .build();

        assertEquals(id, vote.getId());
    }

    @Test
    void nullPassedToConstructor(){
        try {
            VoteId id1 = new VoteId((String) null);
            fail();
        } catch (Exception e){}

        try {
            VoteId id1 = new VoteId((UUID) null);
            fail();
        } catch (Exception e){}
    }

    @Test
    void invalidStringPassedToConstructor(){
        try {
            VoteId id = new VoteId("invalid");
            fail();
        } catch (Exception e){}
    }

    @Test
    void iCanTurnInString(){
        VoteId id = new VoteId();
        VoteId id2 = new VoteId(id.asString());

        assertEquals(id.asString(), id2.asString());
    }
}
