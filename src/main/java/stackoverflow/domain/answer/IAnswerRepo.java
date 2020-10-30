package stackoverflow.domain.answer;

import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.domain.IRepo;

import java.util.Collection;

public interface IAnswerRepo  extends IRepo<Answer, AnswerId> {
    Collection<Answer> find (AnswersQuery query);
}
