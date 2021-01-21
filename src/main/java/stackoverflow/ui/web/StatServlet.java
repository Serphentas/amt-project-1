package stackoverflow.ui.web;

import org.json.JSONArray;
import org.json.JSONObject;
import stackoverflow.ConnectionAPI;
import stackoverflow.application.ServiceReg;
import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="StatServlet", urlPatterns = "/stat")
public class StatServlet extends HttpServlet {

    @Inject
    ServiceReg serviceReg;

    @Resource(lookup = "gamification/users")
    String gamificationUsersURL;

    @Resource(lookup = "gamification/apikey")
    String gamificationKey;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            int nbCommentOfUser = serviceReg.getCommentFacade().getCountCommentOfUser(currentUser.getId()).orElse(0);
            int nbAnswerOfUser = serviceReg.getAnswerFacade().getCountAnswerOfUser(currentUser.getId()).orElse(0);
            int nbQuestionOfUser = serviceReg.getQuestionFacade().getCountQuestionOfUser(currentUser.getId()).orElse(0);
            int nbVoteOfUser = serviceReg.getVoteFacade().getCountVoteOfUser(currentUser.getId()).orElse(0);


            req.setAttribute("nbCommentOfUser", nbCommentOfUser);
            req.setAttribute("nbVoteOfUser", nbVoteOfUser);
            req.setAttribute("nbAnswerOfUser", nbAnswerOfUser);
            req.setAttribute("nbQuestionOfUser", nbQuestionOfUser);
        }

        String jsonTop = new ConnectionAPI().getTop10(gamificationUsersURL + "top10bypoint", gamificationKey);
        JSONArray ar = new JSONObject(jsonTop).getJSONArray("lists");

        ArrayList<String> top10 = new ArrayList<>();
        for (int i = 0; i < ar.length(); i++) {
            top10.add(ar.getJSONObject(i).getString("userId"));
            System.out.println(top10.get(i));
        }

        int nbUser = serviceReg.getIdentityMngmtFacade().getCountUser().orElse(0);
        int nbComment = serviceReg.getCommentFacade().getCountComment().orElse(0);
        int nbAnswer = serviceReg.getAnswerFacade().getCountAnswer().orElse(0);
        int nbQuestion = serviceReg.getQuestionFacade().getCountQuestion().orElse(0);
        int nbVote = serviceReg.getVoteFacade().getCountVote().orElse(0);

        req.setAttribute("top10", top10);
        req.setAttribute("nbUser", nbUser);
        req.setAttribute("nbComment", nbComment);
        req.setAttribute("nbVote", nbVote);
        req.setAttribute("nbAnswer", nbAnswer);
        req.setAttribute("nbQuestion", nbQuestion);

        req.getRequestDispatcher("/WEB-INF/view/stat.jsp").forward(req, resp);
    }
}
