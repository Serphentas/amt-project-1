package stackoverflow.ui.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="StatServlet", urlPatterns = "/stat")
public class StatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //todo récupérer top10 de API
        /*
        String jsonString = "{" +
            "\"lists\": [" +
                "{" +
                    "\"level\": 0," +
                    "\"nbrPoint\": 0," +
                    "\"userId\": \"string\"" +
                "}" +
            "]" +
        "}" +
        JSONObject jsonObject = parser.parse(jsonString);
         */
        ArrayList<String> top10 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            top10.add("user"+i);
        }

        //todo récupérer les variable suivantes ds la db
        int nbComment = 0;
        int nbVote = 0;
        int nbAnswer = 0;
        int nbQuestion = 0;

        req.setAttribute("top10", top10);
        req.setAttribute("nbComment", nbComment);
        req.setAttribute("nbVote", nbVote);
        req.setAttribute("nbAnswer", nbAnswer);
        req.setAttribute("nbQuestion", nbQuestion);

        req.getRequestDispatcher("/WEB-INF/view/stat.jsp").forward(req, resp);
    }
}
