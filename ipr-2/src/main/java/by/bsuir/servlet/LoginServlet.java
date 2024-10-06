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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.sendRedirect("/login");
    }
}
