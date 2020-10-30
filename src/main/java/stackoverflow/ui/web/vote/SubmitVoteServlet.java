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

@WebServlet(name="SubmitVoteServlet", urlPatterns = "/submitVote.do")
public class SubmitVoteServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String qId = req.getParameter("qId");
        String entity = req.getParameter("entity");

        // if ID malformed or not given, redirect to homepage
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException | NullPointerException ex) {
            resp.sendRedirect("/");
            return;
        }

        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");

        if(entity.equals("question")) {
            QuestionId questionId = new QuestionId(id);

            if (serviceReg.getVoteFacade().hasVotedQuestion(questionId, currentUser.getId())) {
                serviceReg.getVoteFacade().unvoteForQuestion(questionId, currentUser.getId());
            } else {
                serviceReg.getVoteFacade().proposeVote(ProposeVoteCmd.builder()
                    .questionId(questionId)
                    .personId(currentUser.getId())
                    .build()
                );
            }
        } else if (entity.equals("comment")) {
            CommentId commentId = new CommentId(id);

            if (serviceReg.getVoteFacade().hasVotedComment(commentId, currentUser.getId())) {
                serviceReg.getVoteFacade().unvoteForComment(commentId, currentUser.getId());
            } else {
                serviceReg.getVoteFacade().proposeVote(ProposeVoteCmd.builder()
                    .commentId(commentId)
                    .personId(currentUser.getId())
                    .build()
                );
            }
        } else if (entity.equals("answer")) {
            AnswerId answerId = new AnswerId(id);

            if (serviceReg.getVoteFacade().hasVotedAnswer(answerId, currentUser.getId())) {
                serviceReg.getVoteFacade().unvoteForAnswer(answerId, currentUser.getId());
            } else {
                serviceReg.getVoteFacade().proposeVote(ProposeVoteCmd.builder()
                    .answerId(answerId)
                    .personId(currentUser.getId())
                    .build()
                );
            }
        }

        resp.sendRedirect("/question?id=" + qId);
    }
}
