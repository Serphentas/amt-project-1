package stackoverflow.domain.question;

import stackoverflow.domain.Id;
import java.util.UUID;

public class QuestionId extends Id {

    public QuestionId() {
        super();
    }

    public QuestionId(String id){
        super(id);
    }

    public QuestionId(UUID id){
        super(id);
    }
}
