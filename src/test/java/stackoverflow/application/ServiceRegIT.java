package stackoverflow.application;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
/*
@RunWith(Arquillian.class)
public class ServiceRegIT {

    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    private ServiceReg serviceReg;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "stackoverflow");
        return archive;
    }

    @Test
    public void arquillianShouldWork(){

    }
}
*/