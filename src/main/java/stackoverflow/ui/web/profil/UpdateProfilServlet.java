package stackoverflow.ui.web.profil;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.UpdateProfilCmd;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="UpdateProfilServlet", urlPatterns = "/profil")
public class UpdateProfilServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        UpdateProfilCmd updateProfilCmd = UpdateProfilCmd.builder()
                .personId(currentUser.getId())
                .firstName("FirstName")
                .lastName("LastName")
                .email("email")
                .build();

        serviceReg.getIdentityMngmtFacade().updateProfil(updateProfilCmd, currentUser);
        req.getSession().removeAttribute("currentUser");
        req.getSession().setAttribute("currentUser",currentUser);
        resp.sendRedirect("/profil");
    }
}
