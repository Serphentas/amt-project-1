package stackoverflow.infrastructure.persistence.memory;

import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.question.IQuestionRepository;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.question.QuestionType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepo implements IQuestionRepository {

    private Map<QuestionId, Question> store = new ConcurrentHashMap<>();

    @Override
    public void save(Question question) {
        store.put((QuestionId) question.getId(), question);
    }

    @Override
    public void remove(QuestionId questionId) {
        store.remove(questionId);
    }

    @Override
    public Optional<Question> findById(QuestionId questionId) {
        Question existingQuestion = store.get(questionId);
        if(existingQuestion == null){
            return Optional.empty();
        }
        Question clonedQuestion = existingQuestion.toBuilder().build();
        return Optional.of(clonedQuestion);
    }

    @Override
    public Collection<Question> findAll() {
        return store.values().stream()
                .map(question -> question.toBuilder().build())
                .collect(Collectors.toList());
    }

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
