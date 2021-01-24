package stackoverflow.domain.question;

import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.IRepo;
import stackoverflow.domain.person.PersonId;

import java.util.Collection;
import java.util.Optional;

public interface IQuestionRepo extends IRepo<Question, QuestionId> {

    Collection<Question> find (QuestionsQuery query);

    Optional<Integer> countAll();
    Optional<Integer> countAllOfUser(PersonId id);
}
