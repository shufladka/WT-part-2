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
    public static void main(String[] args) {
        System.out.println("Server started...");
        int port = 8085;
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
            {
                System.out.printf("New connection accepted [Port: %d]%n", clientSocket.getPort());
                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}