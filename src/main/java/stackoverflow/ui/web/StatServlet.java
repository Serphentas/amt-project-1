package stackoverflow.ui.web;

import stackoverflow.ConnectionAPI;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

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

        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            Object jsonUser = ConnectionAPI.getUser(currentUser.getId().asString());

        }

        Object jsonTop = ConnectionAPI.getTop10();
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
        int nbUser = 0;
        int nbComment = 0;
        int nbAnswer = 0;
        int nbQuestion = 0;

        req.setAttribute("top10", top10);
        req.setAttribute("nbUser", nbUser);
        req.setAttribute("nbComment", nbComment);
        req.setAttribute("nbAnswer", nbAnswer);
        req.setAttribute("nbQuestion", nbQuestion);



        req.getRequestDispatcher("/WEB-INF/view/stat.jsp").forward(req, resp);
    }
}
