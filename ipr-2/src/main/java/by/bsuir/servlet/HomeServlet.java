package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.exceptions.ConnectionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

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

        // селектор языка сайта
        String language = req.getParameter("lang");
        if (language != null) {
            req.getSession().setAttribute("lang", language);
        }

        HttpSession session = req.getSession();
        session.setAttribute("userinfo", "eyJpZCI6MCwidXNlcm5hbWUiOiJzaHVmbGFka2EiLCJmaXJzdF9uYW1lIjoiQUxFSCIsImxhc3RfbmFtZSI6IktMSUFaT1ZJQ0giLCJiaXJ0aF9kYXRlIjoiMjAwMi0wMS0xOCIsImVtYWlsIjoib2xlZ29sZWdvbGVnb2xlZzg4QGdtYWlsLmNvbSIsInJvbGVfaWQiOjEsImlzc3VlZF9pbiI6MTcyODkyMjc1Nn0=");

        resp.sendRedirect("/hotels");
        //req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.sendRedirect("/hotels");
    }

    @Override
    public void destroy() {
        try {
            System.out.println("Hello from HomeServlet DESTROY");
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }

        super.destroy();
    }
}
