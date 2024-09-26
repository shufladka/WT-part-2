package by.bsuir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Задание: Реализовать клиент-серверное приложение и использованием технологии Sockets.
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
 * модификации ее (с использованием DOM-парсера).
 */
public class MainServer {
    private static final int PORT = 8085;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Echo server started and listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    /*
                    // "эхо"
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received from client: " + inputLine);
                        out.println("Echo: " + inputLine);  // Отправляем обратно клиенту
                    }
                     */

                    // Чтение запроса с типом парсера и путём к XML-файлу
                    String xmlPath = in.readLine();
                    String parserType = in.readLine();

                    System.out.println("Received XML path: " + xmlPath);
                    System.out.println("Received parser type: " + parserType);

                    // Эмуляция обработки запроса
                    out.println("Processing XML at: " + xmlPath + " with parser: " + parserType);

                    // Можно добавить логику для выполнения парсинга

                } catch (IOException e) {
                    System.out.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        }
    }
}