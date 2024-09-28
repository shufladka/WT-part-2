package by.bsuir;

import by.bsuir.service.impl.MenuServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Задание: Реализовать клиент-серверное приложение с использованием технологии Sockets.
 * Общие требования к заданию:
 * · Клиент может запросить сервер распарсить xml-файл одним из трех видов парсеров: SAX, StAX, DOM.
 * · Сервер производит анализ xml-документа (соответствующим видом парсера) и отправляет
 * пользователю ответ в виде сформированной коллекции объектов.
 * · Запрос клиента и ответ сервера посылаются через сокет в сериализованном виде.
 * · При получении распаршенной информации клиент восстанавливает данные (десериализует их)
 * и выводит в консоль клиента.
 * · Xml-файл хранится на сервере, для валидации xml-файла необходимо разработать
 * соответствующую xsd-схему.
 * · * Сервер также позволяет использовать для анализа xml-документа парсер JDOM.
 * · **Клиент может отправлять запрос не только о предоставлении информации, но и
 * модификации её (с использованием DOM-парсера).
 */
public class MainClient {
    private static final String HOST = "localhost";
    private static final int PORT = 8085;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to server");

            // Используем MenuServiceImpl для взаимодействия с пользователем
            MenuServiceImpl menuService = new MenuServiceImpl();
            menuService.showMainMenu(in, out);  // Клиент запрашивает ввод и отправляет данные серверу

            // Чтение ответа от сервера
//            String response = in.readLine(); // Чтение единого ответа от сервера
//            System.out.println(response);  // Выводим ответ от сервера

            /*
            // "эхо"
            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input);  // Отправляем введённый текст на сервер
                String response = in.readLine();  // Получаем ответ сервера
                System.out.println("Server echoed: " + response);
            }
             */


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
