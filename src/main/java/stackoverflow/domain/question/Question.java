package stackoverflow.domain.question;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import stackoverflow.domain.IEntity;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity {

    @Setter(AccessLevel.NONE)
    private QuestionId id = new QuestionId();

    private String author;

    private String text;

    @Setter(AccessLevel.NONE)
    private QuestionType questionType;

    public void categorizeAs(QuestionType questionType){this.questionType = questionType;}

    @Override
    public IEntity deepClone() {
        return null;
    }

    public static class QuestionBuilder {

        public Question build(){
            if(id == null) {
                id = new QuestionId();
            }

            if(text==null) {
                text ="";
            }

            if(questionType == null){
                questionType = QuestionType.UNCLASSIFIED;
            }

            if(text.contains("sex")){
                questionType = QuestionType.ADULT;
            }

            return new Question(id, author, text, questionType);
        }
    }
}
