package stackoverflow.ui.web;
import model.Account;
import model.Generator;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ProfilPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Account> model = Generator.getInstance().account();
        request.setAttribute("account", model);
        request.getRequestDispatcher("/WEB-INF/view/profil.jsp").forward(request, response);
    }
}
