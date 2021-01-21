package stackoverflow.ui.web.question;

import stackoverflow.ConnectionAPI;
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


        String userId = currentUser.getId().asString();
        ConnectionAPI.post("postQ", userId);

        // todo récupérer nbAnswers
        int nbAnswers = 0;
        switch(nbAnswers) {
            case 1:
                ConnectionAPI.post("post1Q", userId);
                break;

            case 100:
                ConnectionAPI.post("post100Q", userId);
                break;

            case 1000:
                ConnectionAPI.post("post1000Q", userId);
                break;
        }

        resp.sendRedirect("/questions");
    }
}
