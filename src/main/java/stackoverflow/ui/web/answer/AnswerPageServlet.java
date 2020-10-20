package stackoverflow.ui.web.answer;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswerFacade;
import stackoverflow.application.answer.AnswersDTO;
import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnswerPageHandler", urlPatterns = "/answer")
public class AnswerPageServlet extends HttpServlet {

    private QuestionFacade questionFacade = ServiceReg.getInstance().getQuestionFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("page", "Answer");
        req.setAttribute("question", questionFacade.getQuestionbyId( new QuestionId( req.getParameter("id"))));
        req.getRequestDispatcher("/WEB-INF/view/answer.jsp").forward(req, resp);
    }
}
