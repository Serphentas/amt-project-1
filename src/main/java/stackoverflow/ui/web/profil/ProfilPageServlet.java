package stackoverflow.ui.web.profil;

import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;
import stackoverflow.domain.question.QuestionId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="ProfilPageServlet", urlPatterns = "/profil")
public class ProfilPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO currentUser = (CurrentUserDTO) req.getSession().getAttribute("currentUser");
        req.setAttribute("currentUser", currentUser);
        req.getRequestDispatcher("/WEB-INF/view/profil.jsp").forward(req, resp);
    }
}
