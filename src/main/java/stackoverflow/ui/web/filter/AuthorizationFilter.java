package stackoverflow.ui.web.filter;

import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletReq;
        HttpServletResponse resp = (HttpServletResponse) servletResp;

        if (isPublicResource(req.getRequestURI())) {
            chain.doFilter(req, resp);
            return;
        }

        CurrentUserDTO currentUser = (CurrentUserDTO)req.getSession().getAttribute("currentUser");

        if(currentUser == null){
            String targetUrl = req.getRequestURI();
            if(req.getQueryString() != null){
                targetUrl += "?" + req.getQueryString();
            }

            req.getSession().setAttribute("targetUrl",targetUrl);
            req.getSession().removeAttribute("targetUrl");
            ((HttpServletResponse) servletResp).sendRedirect("/login");
            return;
        }

        chain.doFilter(req, resp);
    }

    boolean isPublicResource(String URI){
        if(URI.startsWith("/assets")){
            return true;
        }

        if(URI.startsWith("/login")){
            return true;
        }

        if(URI.startsWith("/logout")){
            return true;
        }

        if(URI.startsWith("/register")){
            return true;
        }

        return false;
    }
}
