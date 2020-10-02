package stackoverflow.infrastructure.persistence.memory;

import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.question.QuestionType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemoryQuestionRepo extends MemoryRepo<Question, QuestionId> implements IQuestionRepo {

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        if(query != null && query.isSafeForChildren()){
            return findAll().stream()
                    .filter(question -> question.getQuestionType() != QuestionType.ADULT)
                    .collect(Collectors.toList());
        }
        return findAll();
    }
}
