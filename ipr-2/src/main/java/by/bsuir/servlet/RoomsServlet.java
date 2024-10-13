package by.bsuir.servlet;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Address;
import by.bsuir.entity.Hotel;
import by.bsuir.entity.Room;
import by.bsuir.exceptions.ConnectionException;
import by.bsuir.exceptions.DaoException;
import by.bsuir.exceptions.ServiceException;
import by.bsuir.service.AddressService;
import by.bsuir.service.HotelService;
import by.bsuir.service.RoomService;
import by.bsuir.service.ServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/rooms/*")
public class RoomsServlet extends HttpServlet {

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

//        if (session.getAttribute("userinfo") == null) {
//            resp.sendRedirect("/intro");
//        } else {
//            req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
//        }

//        String pathInfo = req.getPathInfo(); // Это даст что-то вроде "/{id}/rooms"
//
//        if (pathInfo != null && !pathInfo.equals("/")) {
//            String id = pathInfo.substring(1); // Извлекаем ID (или другую информацию)
//            req.setAttribute("id", id);
//            System.out.println(id);
//        }


        String hotelId = req.getParameter("hotel_id");

        Hotel hotel = null;
        List<Hotel> hotels = null;
        HotelService hotelService = null;

        Address address = null;
        List<Address> addresses = null;
        AddressService addressService = null;

        Room room = null;
        List<Room> rooms = null;
        RoomService roomService = null;

        try {
            ServiceSingleton service = ServiceSingleton.getInstance();
            hotelService = service.getHotelService();
            addressService = service.getAddressService();
            roomService = service.getRoomService();

            hotels = hotelService.findAll();
            addresses = addressService.findAll();
            rooms = roomService.findAll();

            req.setAttribute("hotels", hotels);
            req.setAttribute("addresses", addresses);
            req.setAttribute("rooms", rooms);
            req.setAttribute("hotel_id", hotelId);

        } catch (ServiceException | DaoException e) {
            System.out.println(e.getMessage());
        }

        // для тестов
        req.getRequestDispatcher("/WEB-INF/rooms.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        // Устанавливаем кодировку для ответа
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

//        String id = req.getParameter("hotel_id");
//        resp.sendRedirect("/rooms/" + req.getParameter("hotel_id"));
        resp.sendRedirect("/rooms");
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
