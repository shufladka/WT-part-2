package by.bsuir;

import by.bsuir.handler.ClientHandler;

import java.io.IOException;
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
            System.out.println("Server started and listening on port " + PORT);

            // Сервер будет работать в бесконечном цикле, обрабатывая подключение клиентов
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                // Обработка клиента в отдельном потоке
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        }
    }
}