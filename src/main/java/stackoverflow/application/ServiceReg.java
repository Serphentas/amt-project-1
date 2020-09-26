package stackoverflow.application;

import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.domain.person.IPersonRepo;
import stackoverflow.infrastructure.persistence.memory.MemoryPersonRepo;

public class ServiceReg {
    private static ServiceReg serviceReg;

    private static IPersonRepo personRepo;
    private static IdentityMngmtFacade identityMngmtFacade;

    private ServiceReg(){
        serviceReg = this;
        personRepo = new MemoryPersonRepo();
        identityMngmtFacade = new IdentityMngmtFacade(personRepo);
    }

    public static ServiceReg getInstance(){
        if(serviceReg == null){
            serviceReg = new ServiceReg();
        }
        return serviceReg;
    }

    public IdentityMngmtFacade getIdentityMngmtFacade() { return identityMngmtFacade; }

}
