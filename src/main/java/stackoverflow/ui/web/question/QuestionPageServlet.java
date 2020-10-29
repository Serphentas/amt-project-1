package stackoverflow.ui.web.question;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.answer.AnswersDTO;
import stackoverflow.application.answer.AnswersQuery;
import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.application.vote.VotesDTO;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.application.answer.AnswersQuery;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionPageServlet  extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO.QuestionDTO question = serviceReg.getQuestionFacade().getQuestionById( new QuestionId( req.getParameter("id")));
        req.setAttribute("question", question);


        //AnswersDTO.AnswerDTO answer = serviceReg.getAnswerFacade().getAnswers(new AnswersQuery(req.getSession().getAttribute("question")).getId());

        req.getRequestDispatcher("/WEB-INF/view/question.jsp").forward(req, resp);
    }
}
