package stackoverflow.ui.web.answer;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswerFacade;
import stackoverflow.application.answer.ProposeAnswerCmd;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SubmitAnswerCommandHandler", urlPatterns = "/answer.do")
public class ProposeAnswerCmdServlet extends HttpServlet {

    private AnswerFacade answerFacade = ServiceReg.getInstance().getAnswerFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*ProposeAnswerCmd command = ProposeAnswerCmd.builder()
                .author("anonymous")
                .text(req.getParameter("text"))
                .build();
        answerFacade.proposeAnswer(command);*/
        resp.sendRedirect("/questions");
    }
}