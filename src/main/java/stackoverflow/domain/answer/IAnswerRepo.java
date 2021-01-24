package stackoverflow.domain.answer;

import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.domain.IRepo;
import stackoverflow.domain.person.PersonId;

import java.util.Collection;
import java.util.Optional;

public interface IAnswerRepo  extends IRepo<Answer, AnswerId> {
    Collection<Answer> find (AnswersQuery query);
    Optional<Integer> countAll();
    Optional<Integer> countAllOfUser(PersonId id);
}
