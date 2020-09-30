package stackoverflow.application;

import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.question.IQuestionRepository;
import stackoverflow.infrastructure.persistence.memory.InMemoryQuestionRepo;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;

public class ServiceReg {
    private static ServiceReg serviceReg;

    private static IPersonRepo personRepo;
    private static IdentityMngmtFacade identityMngmtFacade;

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    private ServiceReg(){
        serviceReg = this;
        personRepo = new MemoryPersonRepo();
        identityMngmtFacade = new IdentityMngmtFacade(personRepo);

        questionRepository = new InMemoryQuestionRepo();
        questionFacade = new QuestionFacade(questionRepository);
    }

    public static ServiceReg getInstance(){
        if(serviceReg == null){
            serviceReg = new ServiceReg();
        }
        return serviceReg;
    }

    public IdentityMngmtFacade getIdentityMngmtFacade() { return identityMngmtFacade; }

    public QuestionFacade getQuestionFacade() {return questionFacade;}
}
