package by.bsuir.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile/*", "/rooms/*", "/orders/*"})
public class AuthFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("userinfo") == null) {
            logger.info("[AuthFilter] The user credentials are invalid");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/intro");
        } else {
            logger.info("[AuthFilter] The user credentials are valid");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}