package stackoverflow.ui.web.question;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.question.ProposeQuestionCmd;
import stackoverflow.application.question.QuestionFacade;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.application.question.QuestionsQuery;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SubmitQuestionCommandHandler", urlPatterns = {"/submitQuestion.do", "/questionsList"})
public class ProposeQuestionCmdServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    private QuestionFacade questionFacade = serviceReg.getQuestionFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProposeQuestionCmd command = ProposeQuestionCmd.builder()
                .author("anonymous")
                .title(req.getParameter("title"))
                .text(req.getParameter("text"))
                .id(new QuestionId())
                .build();
        questionFacade.proposeQuestion(command);
        resp.sendRedirect("/questions");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionsQuery.builder()
                .safeForChildren(false)
                .build());
        req.setAttribute("questions", questionsDTO);
        req.getRequestDispatcher("/WEB-INF/view/questionsList.jsp").forward(req, resp);
    }
}
