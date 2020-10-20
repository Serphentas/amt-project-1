package stackoverflow.application;

import lombok.Getter;
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

    private static ServiceReg serviceReg;

    private IdentityMngmtFacade identityMngmtFacade;
    private QuestionFacade questionFacade;
    private AnswerFacade answerFacade;

    private ServiceReg(){
        serviceReg = this;

        identityMngmtFacade = new IdentityMngmtFacade(personRepo);
        questionFacade = new QuestionFacade(questionRepo);
        answerFacade = new AnswerFacade(answerRepo);
    }

    public static ServiceReg getInstance(){
        if(serviceReg == null){
            serviceReg = new ServiceReg();
        }
        return serviceReg;
    }

    public IdentityMngmtFacade getIdentityMngmtFacade() {
        return identityMngmtFacade;
    }

    public QuestionFacade getQuestionFacade() {
        return questionFacade;
    }

    public AnswerFacade getAnswerFacade() {
        return answerFacade;
    }
}
