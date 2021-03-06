package stackoverflow.ui.web.answer;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswerFacade;
import stackoverflow.application.answer.AnswersDTO;
import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.application.question.QuestionsDTO;
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
import java.util.UUID;

@WebServlet(name = "SubmitAnswerPageServlet", urlPatterns = "/submitAnswer")
public class SubmitAnswerPageServlet extends HttpServlet {

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

        QuestionsDTO.QuestionDTO question = serviceReg.getQuestionFacade().getQuestionById( new QuestionId( req.getParameter("id")));
        req.setAttribute("page", "Answer");
        req.setAttribute("question", question);
        req.getRequestDispatcher("/WEB-INF/view/answer.jsp").forward(req, resp);
    }
}
