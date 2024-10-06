package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.dao.service.impl.AddressDaoImpl;
import by.bsuir.dao.service.impl.PersonDaoImpl;
import by.bsuir.dao.service.impl.RoleDaoImpl;
import by.bsuir.entity.Address;
import by.bsuir.entity.Person;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Hello from HomeServlet INIT");

        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello from HomeServlet SERVICE");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello from HomeServlet DOGET");

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        AddressDaoImpl addressesDao = new AddressDaoImpl();
        List<Address> addresses;

        try {
            addresses = addressesDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("books", addresses);
        req.getRequestDispatcher("WEB-INF/home.jsp").forward(req,resp);

        // селектор языка сайта
        String language = req.getParameter("lang");
        if (language != null) {
            req.getSession().setAttribute("lang", language);
        }

        resp.sendRedirect("/"); // Возвращаемся на предыдущую страницу
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");



        resp.sendRedirect("/");

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
