package by.bsuir.filter;

import by.bsuir.entity.Person;
import by.bsuir.service.AuthService;
import by.bsuir.service.ServiceSingleton;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profiles1/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("userinfo") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/intro");
        } else {

            try {
                ServiceSingleton service = ServiceSingleton.getInstance();
                AuthService authService = service.getAuthService();
                Person person = authService.deserializePersonBase64(session.getAttribute("userinfo").toString());

                if (!authService.isAdmin(person)) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/denied");
                } else {
                    chain.doFilter(request, response);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    @Override
    public void destroy() {
    }
}