package stackoverflow.infrastructure.persistence.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.person.Person;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.infrastructure.persistence.helper.DataSourceProvider;

import java.sql.ResultSet;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNull;

public class JdbcQuestionRepositoryIT {
    static JdbcQuestionRepository repo;

    @BeforeAll
    public static void setupRepository() { repo = new JdbcQuestionRepository(DataSourceProvider.getDataSource()); }

    @Test
    public void findByIdReturnNullWhenNoQuestionIsFound(){
        Question nonExistingQuestion = repo.findById(new QuestionId()).orElse(null);
        assertNull(nonExistingQuestion);
    }

    /*
    @Test
    public void saveShouldWork(){
        repo.save( Question.builder()

        );
    }


    save(Question entity)
    findById(QuestionId id)
    findAll


    Collection<Question> find(QuestionsQuery query)
    resultSetAsList(ResultSet rs)
    getStatement (String cmd)
    searchTags(String questionId)*/

}
