package stackoverflow.ui.web.question;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.application.question.QuestionsQuery;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionsQueryServlet", urlPatterns="/questionsList")
public class QuestionsQueryServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO questionsDTO = serviceReg.getQuestionFacade().getAllQuestions();
        req.setAttribute("questions", questionsDTO.getQuestions());
        req.getRequestDispatcher("/WEB-INF/view/questionsList.jsp").forward(req, resp);
    }
}
