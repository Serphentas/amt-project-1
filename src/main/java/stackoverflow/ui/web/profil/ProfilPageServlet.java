package stackoverflow.ui.web.profil;

import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

//import org.json.simple.*;

@WebServlet(name="ProfilPageServlet", urlPatterns = "/profil")
public class ProfilPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");

        // todo récupération du user de l'API
/*
        String json = "{" +
          "\"LadderOfUser\": { " +
            "\"level\": 0," +
            "\"nbrPoint\": 0," +
            "\"title\": \"string\"" +
          "}," +
          "\"badges\": [" +
            "{" +
              "\"description\": \"string\"," +
              "\"name\": \"string\"" +
            "}" +
          "]," +
          "\"nbrPointOfUser\": 0," +
          "\"userId\": \"string\"" +
        "}";
        JSONObject jsonObject = parser.parse(json);
*/
        String level = "5";
        String exp = "500";
        String nbExp = "50";
        ArrayList<String> badges = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            badges.add( "badge"+i);
        }

        req.setAttribute("currentUser", currentUser);
        req.setAttribute("level", level);
        req.setAttribute("exp", exp);
        req.setAttribute("nbExp", nbExp);
        req.setAttribute("badges", badges);

        req.getRequestDispatcher("/WEB-INF/view/profil.jsp").forward(req, resp);
    }
}
