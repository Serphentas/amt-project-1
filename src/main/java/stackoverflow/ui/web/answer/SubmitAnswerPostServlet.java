package stackoverflow.ui.web.answer;

import stackoverflow.ConnectionAPI;
import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.ProposeAnswerCmd;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name="SubmitAnswerCmdServlet", urlPatterns = "/submitAnswer.do")
public class SubmitAnswerPostServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Resource(lookup = "gamification/events")
    String gamificationEventURL;

    @Resource(lookup = "gamification/apikey")
    String gamificationKey;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // if ID malformed or not given, redirect to homepage
        try {
            UUID.fromString(idParam);
        } catch (IllegalArgumentException | NullPointerException ex) {
            resp.sendRedirect("/");
            return;
        }
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        QuestionId questionId = new QuestionId(req.getParameter("id"));
        ProposeAnswerCmd command = ProposeAnswerCmd.builder()
                .personId(currentUser.getId())
                .questionId(questionId)
                .text(req.getParameter("text"))
                .build();
        serviceReg.getAnswerFacade().proposeAnswer(command);

        String userId = currentUser.getId().asString();
        new ConnectionAPI().post("postR", userId, gamificationEventURL, gamificationKey);

        int nbAnswers = serviceReg.getAnswerFacade().getCountAnswerOfUser(currentUser.getId()).orElse(0);
        switch(nbAnswers) {
            case 1:
                new ConnectionAPI().post("post1R", userId, gamificationEventURL, gamificationKey);
                break;

            case 100:
                new ConnectionAPI().post("post100R", userId, gamificationEventURL, gamificationKey);
                break;

            case 1000:
                new ConnectionAPI().post("post1000R", userId, gamificationEventURL, gamificationKey);
                break;
        }

        resp.sendRedirect("/question?id=" + questionId.asString());
    }
}