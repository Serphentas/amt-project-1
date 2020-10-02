package stackoverflow.application;

import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.infrastructure.persistence.memory.MemoryQuestionRepo;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;

public class ServiceReg {
    private static ServiceReg serviceReg;

    private static IPersonRepo personRepo;
    private static IdentityMngmtFacade identityMngmtFacade;

    private static IQuestionRepo questionRepo;
    private static QuestionFacade questionFacade;

    private ServiceReg(){
        serviceReg = this;

        personRepo = new MemoryPersonRepo();
        identityMngmtFacade = new IdentityMngmtFacade(personRepo);

        questionRepo = new MemoryQuestionRepo();
        questionFacade = new QuestionFacade(questionRepo);
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
