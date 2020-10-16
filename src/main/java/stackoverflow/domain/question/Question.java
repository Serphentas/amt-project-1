package stackoverflow.domain.question;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.person.PersonId;

@Data
@Getter
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    @Setter(AccessLevel.NONE)
    private QuestionId id = new QuestionId();

    private String author;
    private String title;
    private String text;

    @Setter(AccessLevel.NONE)
    private QuestionType questionType;

    public void categorizeAs(QuestionType questionType){ this.questionType = questionType; }

    @Override
    public Question deepClone() {
        return this.toBuilder()
            .id(new QuestionId(id.asString()))
            .build();
    }

    public static class QuestionBuilder {

        public Question build(){
            if(id == null) {
                id = new QuestionId();
            }

            if(title==null) {
                text ="";
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

            return new Question(id, author, title, text, questionType);
        }
    }
}
