package stackoverflow.ui.web.filter;

import stackoverflow.application.identitymngmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

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

    @Override
    public void destroy() {

    }

    boolean isPublicResource(String URI){
        if(URI.startsWith("/")){
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
