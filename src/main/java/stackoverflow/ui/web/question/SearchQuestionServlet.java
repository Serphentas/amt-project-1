package stackoverflow.ui.web.question;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.application.ServiceReg;
import stackoverflow.application.question.QuestionsQuery;

@WebServlet(name = "SearchQuestionServlet", urlPatterns = "/search")
public class SearchQuestionServlet extends HttpServlet {
    @Inject
    ServiceReg serviceReg;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String keyword = request.getParameter("title");

        // if keyword isn't set, redirect to homepage
        if (keyword == null || keyword.length() == 0) {
            response.sendRedirect("/");
        }

        request.setAttribute(
            "questions",
            serviceReg.getQuestionFacade().getQuestions(QuestionsQuery.builder()
                .title(keyword)
                .build()
            )
        );
        request.getRequestDispatcher("/WEB-INF/view/questionsList.jsp").forward(request, response);
    }
}
