package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Person;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/orders/*")
public class OrderServlet extends HttpServlet {
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

        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            String id = pathInfo.substring(1);
            req.setAttribute("id", id);
        }

        req.getRequestDispatcher("/WEB-INF/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String roomId = req.getParameter("chosen_room_id");
        String orderId = req.getParameter("chosen_order_id");
        String pathPart = null;

        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            pathPart = pathInfo.substring(1);
        }

        try {
            ServiceSingleton service = ServiceSingleton.getInstance();
            AuthService authService = service.getAuthService();
            OrderService orderService = service.getOrderService();

            HttpSession session = req.getSession();
            if (session != null || session.getAttribute("userinfo") != null) {
                Person person = authService.deserializePersonBase64(session.getAttribute("userinfo").toString());

                if (pathPart != null) {
                    switch (pathPart) {
                        case "save":
                            orderService.save(person, Integer.parseInt(roomId));
                            break;
                        case "update":
                            orderService.update(orderService.findById(Integer.parseInt(orderId)));
                            break;
                        case "delete":
                            orderService.delete(Integer.parseInt(orderId));
                            break;
                        default:
                            req.setAttribute("id", pathPart);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/orders");
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
