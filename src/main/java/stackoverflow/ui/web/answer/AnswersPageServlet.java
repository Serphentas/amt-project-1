package stackoverflow.ui.web.answer;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswerFacade;
import stackoverflow.application.answer.AnswersDTO;
import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnswerPageHandler", urlPatterns = "/answer")
public class AnswersPageServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    private AnswerFacade answerFacade = serviceReg.getAnswerFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*AnswersDTO answersDTO = answerFacade.getAnswers(AnswersQuery.builder()
            .id(new QuestionId(req.getParameter("id")))
            .build());
        req.setAttribute("answer", answersDTO);*/
        req.getRequestDispatcher("/WEB-INF/view/answer.jsp").forward(req, resp);
    }
}
