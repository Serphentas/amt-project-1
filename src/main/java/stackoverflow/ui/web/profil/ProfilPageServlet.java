package stackoverflow.ui.web.profil;

import stackoverflow.ConnectionAPI;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import org.json.*;

@WebServlet(name="ProfilPageServlet", urlPatterns = "/profil")
public class ProfilPageServlet extends HttpServlet {

    @Resource(lookup = "gamification/users")
    String gamificationUserURL;

    @Resource(lookup = "gamification/apikey")
    String gamificationKey;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");

        String jsonString = new ConnectionAPI().getUser(currentUser.getId().asString(), gamificationUserURL, gamificationKey);
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONObject ladder = jsonObject.getJSONObject("LadderOfUser");
        String level = ladder.getInt("level") + " " + ladder.getString("title");
        int exp = jsonObject.getInt("nbrPointOfUser");

        JSONArray jsonBadges = jsonObject.getJSONArray("badges");
        ArrayList<String> badges = new ArrayList<>();
        for (int i = 0; i < jsonBadges.length(); i++) {
            badges.add( jsonBadges.getJSONObject(i).getString("name"));
        }

        req.setAttribute("currentUser", currentUser);
        req.setAttribute("level", level);
        req.setAttribute("exp", exp);
        req.setAttribute("badges", badges);

        req.getRequestDispatcher("/WEB-INF/view/profil.jsp").forward(req, resp);
    }
}
