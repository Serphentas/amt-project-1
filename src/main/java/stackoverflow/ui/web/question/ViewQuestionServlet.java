package stackoverflow.ui.web.question;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswersDTO;
import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.application.comment.CommentQuery;
import stackoverflow.application.comment.CommentsDTO;
import stackoverflow.application.question.QuestionsDTO.QuestionDTO;
import stackoverflow.domain.question.QuestionId;

@WebServlet(name="ViewQuestionServlet", urlPatterns="/question")
public class ViewQuestionServlet extends HttpServlet {

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

        QuestionId idQuestion = new QuestionId(idParam);
        QuestionDTO question = serviceReg.getQuestionFacade().getQuestionById(idQuestion);
        AnswersDTO answers = serviceReg.getAnswerFacade().getAnswers(AnswersQuery.builder()
            .id(idQuestion)
            .build()
        );
        CommentsDTO comments = serviceReg.getCommentFacade().getQuestionComments(CommentQuery.builder()
                .id(UUID.fromString(idQuestion.asString()))
                .build()
        );
        req.setAttribute("question", question);
        req.setAttribute("answers", answers);
        req.setAttribute("votes", serviceReg.getVoteFacade().nbrVoteQuestion(idQuestion));
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/WEB-INF/view/question.jsp").forward(req, resp);
    }
}
