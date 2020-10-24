package stackoverflow.domain.question;

import lombok.*;
import stackoverflow.domain.IEntity;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.tag.Tag;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    private QuestionId id;
    private PersonId idUser;

    private String author;
    private String title;
    private String text;

    private Collection<Tag> tags;

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

            if(text == null || text.isEmpty()){
                throw new IllegalArgumentException("Text is mandatory");
            }

            if(title == null || title.isEmpty()){
                throw new IllegalArgumentException("Title is mandatory");
            }

            if(questionType == null){
                questionType = QuestionType.UNCLASSIFIED;
            }

            if(text.contains("sex")){
                questionType = QuestionType.ADULT;
            }

            return new Question(id, idUser, author, title, text, tags, questionType);
        }
    }
}
