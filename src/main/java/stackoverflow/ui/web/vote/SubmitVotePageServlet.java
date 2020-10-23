package stackoverflow.ui.web.vote;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitVotePageServlet", urlPatterns = "/submitVote")
public class SubmitVotePageServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO.QuestionDTO entity = serviceReg.getQuestionFacade().getQuestionById( new QuestionId( req.getParameter("id")));
        req.setAttribute("entity", entity);
        req.getRequestDispatcher("/WEB-INF/view/vote.jsp").forward(req, resp);
    }
}
