package stackoverflow.application.question;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProposeQuestionCmdTest {

    @Test
    void defaultValuesAreProvided(){
        ProposeQuestionCmd q = ProposeQuestionCmd.builder().build();
        assertEquals("Anonymous", q.getAuthor());
        assertEquals("No content", q.getText());
    }


}
