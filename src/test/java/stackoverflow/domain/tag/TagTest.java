package stackoverflow.domain.tag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TagTest {
    private Tag tag;

    @BeforeEach
    void setupTag(){
        tag = Tag.builder()
                .tag("java")
                .build();
    }

    @Test
    void deepCloneShouldGiveACopy(){
        Tag TagCopy = tag.deepClone();
        TagCopy.setTag("C++");

        assertNotEquals(TagCopy,tag);
    }
}
