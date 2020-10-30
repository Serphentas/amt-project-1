
import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswersDTO;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitCommentPageServlet", urlPatterns = "/submitComment")
public class SubmitCommentPageServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO.QuestionDTO question = serviceReg.getQuestionFacade().getQuestionById( new QuestionId( req.getParameter("id")));
        AnswersDTO.AnswerDTO answer = serviceReg.getAnswerFacade().getAnswerById( new AnswerId( req.getParameter("id")));

        req.setAttribute("page", "comment");
        if( question != null){
            req.setAttribute("entity", question);
            req.setAttribute("entityName", "question");
        } else{
            req.setAttribute("entity", answer);
            req.setAttribute("entityName", "answer");
        }

        req.getRequestDispatcher("/WEB-INF/view/comment.jsp").forward(req, resp);
    }
}
