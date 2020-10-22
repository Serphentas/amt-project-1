package stackoverflow.domain.question;

import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.IRepo;

import java.util.Collection;

public interface IQuestionRepo extends IRepo<Question, QuestionId> {

    Collection<Question> find (QuestionsQuery query);
}
