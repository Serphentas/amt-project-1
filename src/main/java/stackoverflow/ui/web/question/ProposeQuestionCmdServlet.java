package stackoverflow.ui.web.question;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.question.ProposeQuestionCmd;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SubmitQuestionCommandServlet", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCmdServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        ProposeQuestionCmd command = ProposeQuestionCmd.builder()
                .personId(currentUser.getId())
                .title(req.getParameter("title"))
                .text(req.getParameter("text"))
                .id(new QuestionId())
                .build();
        serviceReg.getQuestionFacade().proposeQuestion(command);
        resp.sendRedirect("/questionsList");
    }
}
