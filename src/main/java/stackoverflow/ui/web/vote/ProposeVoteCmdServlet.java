package stackoverflow.ui.web.vote;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.ProposeAnswerCmd;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SubmitVoteCmdServlet", urlPatterns = "/submitVote.do")
public class ProposeVoteCmdServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        QuestionId questionId = new QuestionId(req.getParameter("id"));
        ProposeAnswerCmd command = ProposeAnswerCmd.builder()
                .id(new AnswerId())
                .personId(currentUser.getId())
                .questionId(questionId)
                .text(req.getParameter("text"))
                .build();
        serviceReg.getAnswerFacade().proposeAnswer(command);
        resp.sendRedirect("/questionsList");
    }
}