package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.*;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

        String pathPart = null;
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            pathPart = pathInfo.substring(1);
        }

        List<Room> rooms;
        RoomService roomService;

        List<Address> addresses;
        AddressService addressService;

        List<Hotel> hotels;
        HotelService hotelService;

        Person person ;
        List<Person> people;
        PersonService personService;

        List<Order> orders;
        OrderService orderService;

        try {
            ServiceSingleton service = ServiceSingleton.getInstance();
            AuthService authService = service.getAuthService();

            personService = service.getPersonService();
            people = personService.findAll();
            req.setAttribute("people", people);


            roomService = service.getRoomService();
            rooms = roomService.findAll();
            req.setAttribute("rooms", rooms);

            addressService = service.getAddressService();
            addresses = addressService.findAll();
            req.setAttribute("addresses", addresses);

            hotelService = service.getHotelService();
            hotels = hotelService.findAll();
            req.setAttribute("hotels", hotels);

            orderService = service.getOrderService();
            orders = orderService.findAll();
            req.setAttribute("orders", orders);

            HttpSession session = req.getSession();
            if (session != null || session.getAttribute("userinfo") != null) {
                person = authService.deserializePersonBase64(session.getAttribute("userinfo").toString());
                boolean isAdmin = authService.isAdmin(person);

                if (pathPart != null) {
                    req.setAttribute("order_id", pathPart);
                }

                if (person != null) {
                    req.setAttribute("is_admin", String.valueOf(isAdmin));
                    req.setAttribute("person_id", String.valueOf(person.getId()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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

        List<Room> rooms;
        RoomService roomService;

        List<Address> addresses;
        AddressService addressService;

        List<Hotel> hotels;
        HotelService hotelService;

        Person person;
        List<Person> people;
        PersonService personService;

        List<Order> orders;
        OrderService orderService;

        try {
            ServiceSingleton service = ServiceSingleton.getInstance();
            AuthService authService = service.getAuthService();

            personService = service.getPersonService();
            people = personService.findAll();
            req.setAttribute("people", people);

            roomService = service.getRoomService();
            rooms = roomService.findAll();
            req.setAttribute("rooms", rooms);

            addressService = service.getAddressService();
            addresses = addressService.findAll();
            req.setAttribute("addresses", addresses);

            hotelService = service.getHotelService();
            hotels = hotelService.findAll();
            req.setAttribute("hotels", hotels);

            orderService = service.getOrderService();
            orders = orderService.findAll();
            req.setAttribute("orders", orders);

            HttpSession session = req.getSession();
            if (session != null || (session.getAttribute("userinfo") != null)) {
                person = authService.deserializePersonBase64(session.getAttribute("userinfo").toString());

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
                            req.setAttribute("order_id", pathPart);
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
