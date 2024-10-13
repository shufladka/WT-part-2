package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.ServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember_me");

        Person person = null;

        try {
            ServiceSingleton service = ServiceSingleton.getInstance();
            person = service.getAuthService().login(username, password);

            // Сериализация объекта Person с добавлением произвольных параметров и хешированием в Base64
            String hash = service.getAuthService().serializePersonBase64(
                    person,
                    "issued_in", Instant.now().getEpochSecond()
            );

            HttpSession session = req.getSession();

            if (Objects.equals(rememberMe, "on")) {

                // Сессия удаляется, если страница не открывалась один час (по умолчанию 1800 секунд)
                session.setMaxInactiveInterval(3600);

            } else {

                // Сессия удаляется после закрытия вкладки (по умолчанию 1800 секунд)
                session.setMaxInactiveInterval(-1);
            }

            session.setAttribute("userinfo", hash);

        } catch (ServiceException | DaoException e) {
            System.out.println(e.getMessage());
        }

        if (person != null) {
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/login?error=not_exists");
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }

        super.destroy();
    }
}
