package stackoverflow.ui.web.login;


import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.IdentityMngmtFacade;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateCmd;
import stackoverflow.application.identitymngmt.authenticate.AuthenticateFailedException;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="LoginCmdServlet", urlPatterns = "/login.do")
public class LoginCmdServlet extends HttpServlet {

    private ServiceReg serviceReg = ServiceReg.getInstance();
    private IdentityMngmtFacade identityMngmtFacade = serviceReg.getIdentityMngmtFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().getAttribute("errors");
        CurrentUserDTO currentUser = null;

        AuthenticateCmd authenticateCmd = AuthenticateCmd.builder()
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("password"))
                .build();

        try {
            currentUser = identityMngmtFacade.authenticate(authenticateCmd);
            req.getSession().setAttribute("currentUser", currentUser);
            String targetUrl = (String)req.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : "/";
            resp.sendRedirect(targetUrl);
            return;
        } catch ( AuthenticateFailedException e) {
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/login");
        }
    }
}
