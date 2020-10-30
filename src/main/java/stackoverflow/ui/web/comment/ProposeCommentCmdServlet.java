package stackoverflow.ui.web.Comment;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.comment.ProposeCommentCmd;
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
import java.util.UUID;

@WebServlet(name="SubmitCommentCmdServlet", urlPatterns = "/submitComment.do")
public class ProposeCommentCmdServlet extends HttpServlet {

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
        AnswerId answerId = null;

        if(req.getParameter("entity").equals("question")) {
            questionId = new QuestionId(req.getParameter("id"));
        }
        else {
            answerId = new AnswerId(req.getParameter("id"));
        }

        ProposeCommentCmd command = ProposeCommentCmd.builder()
                .personId(currentUser.getId())
                .questionId(questionId)
                .answerId(answerId)
                .text(req.getParameter("text"))
                .build();
        serviceReg.getCommentFacade().proposeComment(command);
        resp.sendRedirect("/questions");
    }
}