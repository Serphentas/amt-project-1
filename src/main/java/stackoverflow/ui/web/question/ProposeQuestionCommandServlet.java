package stackoverflow.ui.web.question;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.question.ProposeQuestionCommand;
import stackoverflow.application.question.QuestionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SubmitQuestionCommandHandler", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandServlet extends HttpServlet {

    private ServiceReg serviceRegistry = ServiceReg.getInstance();
    private QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProposeQuestionCommand command = ProposeQuestionCommand.builder()
                .author("anonymous")
                .text(req.getParameter("text"))
                .build();
        questionFacade.proposeQuestion(command);
        resp.sendRedirect("/questions");
    }
}
