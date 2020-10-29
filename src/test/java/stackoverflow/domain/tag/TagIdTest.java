package stackoverflow.domain.tag;

import org.junit.jupiter.api.Test;
import stackoverflow.domain.tag.Tag;
import stackoverflow.domain.tag.TagId;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class TagIdTest {
    @Test
    void iCanUseTagId(){
        TagId id = new TagId();
        Tag tag = Tag.builder()
                .tagId(id)
                .tag("java")
                .build();

        assertEquals(id, tag.getId());
    }

    @Test
    void nullPassedToConstructor(){
        try {
            TagId id1 = new TagId((String) null);
            fail();
        } catch (Exception e){}

        try {
            TagId id1 = new TagId((UUID) null);
            fail();
        } catch (Exception e){}
    }

    @Test
    void invalidStringPassedToConstructor(){
        try {
            TagId id = new TagId("invalid");
            fail();
        } catch (Exception e){}
    }

    @Test
    void iCanTurnInString(){
        TagId id = new TagId();
        TagId id2 = new TagId(id.asString());

        assertEquals(id.asString(), id2.asString());
    }
}
