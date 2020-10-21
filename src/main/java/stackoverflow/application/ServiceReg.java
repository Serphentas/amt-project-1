package stackoverflow.application;

import stackoverflow.application.answer.AnswerFacade;
import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.domain.answer.IAnswerRepo;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.question.IQuestionRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceReg {
    @Inject @Named("JdbcUserRepository")
    IPersonRepo personRepo;

    @Inject @Named("JdbcQuestionRepository")
    IQuestionRepo questionRepo;

    @Inject @Named("JdbcAnswerRepository")
    IAnswerRepo answerRepo;

    public IdentityMngmtFacade getIdentityMngmtFacade() {
        return new IdentityMngmtFacade(personRepo);
    }

    public QuestionFacade getQuestionFacade() {
        return new QuestionFacade(questionRepo);
    }

    public AnswerFacade getAnswerFacade() {
        return new AnswerFacade(answerRepo);
    }
}
