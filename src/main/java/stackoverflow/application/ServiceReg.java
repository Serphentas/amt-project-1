package stackoverflow.application;

import stackoverflow.application.answer.AnswerFacade;
import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.application.vote.VoteFacade;
import stackoverflow.domain.answer.IAnswerRepo;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.vote.IVoteRepo;

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

    @Inject @Named("JdbcAnswerRepository")
    IVoteRepo voteRepo;

/*
    public static ServiceReg instance = new ServiceReg();

    protected ServiceReg(){}

    public ServiceReg getInstance(){
        return instance;
    }
*/
    public IdentityMngmtFacade getIdentityMngmtFacade() {
        return new IdentityMngmtFacade(personRepo);
    }

    public QuestionFacade getQuestionFacade() {
        return new QuestionFacade(questionRepo);
    }

    public VoteFacade getVoteFacade() {return new VoteFacade(voteRepo);}

    public AnswerFacade getAnswerFacade() {
        return new AnswerFacade(answerRepo);
    }
}
