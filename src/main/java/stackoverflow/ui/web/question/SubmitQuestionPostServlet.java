package stackoverflow.ui.web.question;

import stackoverflow.ConnectionAPI;
import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.question.ProposeQuestionCmd;

import javax.annotation.Resource;
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

    @Resource(lookup = "gamification/events")
    String gamificationEventURL;

    @Resource(lookup = "gamification/apikey")
    String gamificationKey;

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
        new ConnectionAPI().post("postQ", userId, gamificationEventURL, gamificationKey);

        // todo récupérer nbAnswers
        int nbAnswers = 0;
        switch(nbAnswers) {
            case 1:
                new ConnectionAPI().post("post1Q", userId, gamificationEventURL, gamificationKey);
                break;

            case 100:
                new ConnectionAPI().post("post100Q", userId, gamificationEventURL, gamificationKey);
                break;

            case 1000:
                new ConnectionAPI().post("post1000Q", userId, gamificationEventURL, gamificationKey);
                break;
        }

        resp.sendRedirect("/questions");
    }
}
