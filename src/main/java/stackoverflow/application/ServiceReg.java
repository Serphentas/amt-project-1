package stackoverflow.application;

import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.infrastructure.persistence.memory.MemoryQuestionRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceReg {
    @Inject @Named("JdbcUserRepository")
    IPersonRepo personRepo;

    IQuestionRepo questionRepo;

    public IdentityMngmtFacade getIdentityMngmtFacade() {
        return new IdentityMngmtFacade(personRepo);
    }

    public QuestionFacade getQuestionFacade() {
        return new QuestionFacade(questionRepo);
    }
}
