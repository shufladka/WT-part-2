package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            logger.error("[RegistrationServlet] {}", e.getMessage());
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

        logger.error("[RegistrationServlet] [GET] RequestDispatcher 'WEB-INF/registration.jsp'");
        req.getRequestDispatcher("WEB-INF/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Person person = new Person();
        person.setFirstName(req.getParameter("first_name"));
        person.setLastName(req.getParameter("last_name"));
        person.setUsername(req.getParameter("username"));
        person.setPassword(req.getParameter("password"));
        person.setBirthDate(LocalDate.parse(req.getParameter("birth_date")));
        person.setRoleId(1);  // USER
        person.setEmail(req.getParameter("email"));

        boolean isRegisterSuccess = false;

        try {
            ServiceFactory service = ServiceFactory.getInstance();
            isRegisterSuccess = service.getAuthService().registration(person);
        } catch (ServiceException | DaoException e) {
            logger.error("[RegistrationServlet] {}", e.getMessage());
        }

        if (isRegisterSuccess) {
            logger.info("[RegistrationServlet] [POST] Redirect to '/login'");
            resp.sendRedirect("/login");
        } else {
            logger.info("[RegistrationServlet] [POST] Redirect to '/registration?error=exists'");
            resp.sendRedirect("/registration?error=exists");
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            logger.error("[RegistrationServlet] {}", e.getMessage());
        }

        super.destroy();
    }
}