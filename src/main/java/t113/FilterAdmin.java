package t113;

import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class FilterAdmin implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session.getAttribute("admin") != null);


        if (isLoggedIn) {

            chain.doFilter(req, resp);

        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);

        }

    }

    public FilterAdmin() {
    }

    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
