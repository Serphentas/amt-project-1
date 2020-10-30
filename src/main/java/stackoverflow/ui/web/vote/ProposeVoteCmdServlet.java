package stackoverflow.ui.web.vote;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.application.vote.ProposeVoteCmd;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name="SubmitVoteCmdServlet", urlPatterns = "/submitVote.do")
public class ProposeVoteCmdServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

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

        QuestionId questionId = null;
        CommentId commentId = null;

        if(req.getParameter("entity").equals("question")) {
            questionId = new QuestionId(req.getParameter("id"));
        }
        else {
            commentId = new CommentId(req.getParameter("id"));
        }

        ProposeVoteCmd command = ProposeVoteCmd.builder()
                .personId(currentUser.getId())
                .questionId(questionId)
                .commentId(commentId)
                .build();
        serviceReg.getVoteFacade().proposeVote(command);
        resp.sendRedirect("/questionsList");
    }
}
