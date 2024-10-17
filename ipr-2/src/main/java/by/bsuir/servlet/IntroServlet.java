package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/intro")
public class IntroServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(IntroServlet.class);

    @Override
    public void init() throws ServletException {

        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            logger.error("[IntroServlet] {}", e.getMessage());
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // селектор языка сайта
        String language = req.getParameter("lang");
        if (language != null) {
            req.getSession().setAttribute("lang", language);
        }

        logger.info("[IntroServlet] [GET] RequestDispatcher '/WEB-INF/intro.jsp'");
        req.getRequestDispatcher("WEB-INF/intro.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        logger.info("[HotelsServlet] [POST] Redirect to '/'");
        resp.sendRedirect("/");
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            logger.error("[IntroServlet] {}", e.getMessage());
        }

        super.destroy();
    }
}
