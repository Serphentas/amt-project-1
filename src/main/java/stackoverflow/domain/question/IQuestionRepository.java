package stackoverflow.domain.question;

import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.IRepo;

import java.util.Collection;

public interface IQuestionRepository extends IRepo<Question, QuestionId> {
    public Collection<Question> find (QuestionsQuery query);
}
