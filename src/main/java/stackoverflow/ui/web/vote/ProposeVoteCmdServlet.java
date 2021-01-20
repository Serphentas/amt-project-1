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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

            if (serviceReg.getVoteFacade().hasVotedQuestion(questionId, currentUser.getId())) {
                serviceReg.getVoteFacade().unvoteForQuestion(questionId, currentUser.getId());
            } else {
                serviceReg.getVoteFacade().proposeVote(ProposeVoteCmd.builder()
                    .questionId(questionId)
                    .personId(currentUser.getId())
                    .build()
                );

                trySendAPi(currentUser.getId().asString());
            }
        }
        else {
            commentId = new CommentId(req.getParameter("id"));

            if (serviceReg.getVoteFacade().hasVotedComment(commentId, currentUser.getId())) {
                serviceReg.getVoteFacade().unvoteForComment(commentId, currentUser.getId());
            } else {
                serviceReg.getVoteFacade().proposeVote(ProposeVoteCmd.builder()
                    .commentId(commentId)
                    .personId(currentUser.getId())
                    .build()
                );

                trySendAPi(currentUser.getId().asString());
            }
        }

        resp.sendRedirect("/question?id=" + questionId.asString());
    }

    private void trySendAPi( String userId) {
        // todo il faut savoir si l'utilisateur a déja voté/a recu le badge vote
        boolean hasVoteBadge = false;
        if(hasVoteBadge) {
            String type = "vote";
            String timestamp = java.time.Clock.systemUTC().instant().toString();
            String json = "{\"properties\": {},\"timestamp\": " + timestamp + ",\"type\": " + type + ",\"userId\": " + userId + " }";
            // todo envoi event vers api
        }
    }
}
