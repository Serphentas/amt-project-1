package stackoverflow.application.question;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionsQueryTest {

    @Test
    void defaultValuesAreProvided(){
        QuestionsQuery query = QuestionsQuery.builder().build();
        assertTrue(query.isSafeForChildren());
    }
}
