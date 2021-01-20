package stackoverflow.ui.web.question;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.question.ProposeQuestionCmd;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SubmitQuestionPostServlet", urlPatterns = "/submitQuestion.do")
public class SubmitQuestionPostServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        ProposeQuestionCmd command = ProposeQuestionCmd.builder()
                .userId(currentUser.getId())
                .title(req.getParameter("title"))
                .text(req.getParameter("text"))
                .build();
        serviceReg.getQuestionFacade().proposeQuestion(command);

        String type = null;
        String timestamp = java.time.Clock.systemUTC().instant().toString();
        String userId = currentUser.getId().asString();

        String json = "{\"properties\": {},\"timestamp\": " + timestamp + ",\"type\": postQ,\"userId\": " + userId + " }";
        //todo envoi event vers API

        // todo récupérer nbAnswers
        int nbAnswers = 0;
        switch(nbAnswers) {
            case 1:
                type = "post1Q";
                break;

            case 100:
                type = "post100Q";
                break;

            case 1000:
                type = "post1000Q";
                break;
        }
        if(type != null) {
            json = "{\"properties\": {},\"timestamp\": " + timestamp + ",\"type\": " + type + ",\"userId\": " + userId + " }";
            // todo envoi event vers api
        }

        resp.sendRedirect("/questions");
    }
}
