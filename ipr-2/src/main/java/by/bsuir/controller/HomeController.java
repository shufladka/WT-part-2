package by.bsuir.controller;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.dao.service.impl.PersonDaoImpl;
import by.bsuir.dao.service.impl.RoleDaoImpl;
import by.bsuir.entity.Person;
import by.bsuir.entity.Role;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.exceptions.DaoException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class HomeController extends HttpServlet {

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

//        RoleDaoImpl roleDao = new RoleDaoImpl();
//        List<Role> roles;

        PersonDaoImpl personDao = new PersonDaoImpl();
        List<Person> persons;
        try {
            persons = personDao.findByBirthDate(LocalDate.now());
//            persons = personDao.findByName("Вася", "Пупкин");
//            persons = personDao.findAll();
//            roles = roleDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("books", persons);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req,resp);

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
