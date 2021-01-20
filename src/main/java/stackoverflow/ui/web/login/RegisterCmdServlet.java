package stackoverflow.ui.web.login;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.login.ProposeRegisterCmd;
import stackoverflow.application.identitymngmt.login.RegistrationFailedException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@WebServlet(name = "RegisterCmdServlet", urlPatterns = "/register.do")
public class RegisterCmdServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Resource(lookup = "gamification/badges")
    String gamificationURL;
    @Resource(lookup = "gamification/apikey")
    String gamificationAPIKey;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().getAttribute("errors");

        System.out.println(gamificationURL);

        ProposeRegisterCmd proposeRegisterCmd = ProposeRegisterCmd.builder()
            .username(req.getParameter("username"))
            .email(req.getParameter("email"))
            .firstName(req.getParameter("firstName"))
            .lastName(req.getParameter("lastName"))
            .clearTextPassword(req.getParameter("password"))
            .confirmPassword(req.getParameter("confirmPassword"))
            .build();

        try {
            serviceReg.getIdentityMngmtFacade().register(proposeRegisterCmd);

            URL url = new URL(gamificationURL);
            String data = String.format(
                "{" +
                    "\"%s\": \"%s\"" +
                "}",
                "asd", "123"
            );
            HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("x-api-key", gamificationAPIKey);
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();

            req.getRequestDispatcher("/login.do").forward(req, resp);
            return;
        } catch(RegistrationFailedException e) {
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("/login");
        }
    }
}
