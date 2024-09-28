package by.bsuir.handler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String xmlPath;
            String parserType;

            while (true) {
                // Чтение запроса с типом парсера и путём к XML-файлу
                xmlPath = in.readLine();
                if (xmlPath == null || xmlPath.equalsIgnoreCase("exit")) {
                    System.out.println("Client requested disconnection.");
                    break;
                }

                parserType = in.readLine();
                if (parserType == null) {
                    System.out.println("Client requested disconnection.");
                    break;
                }

                System.out.println("Received XML path: " + xmlPath);
                System.out.println("Received parser type: " + parserType);

                // Обработка запроса
                StringBuilder result = new StringBuilder();

                // Если выбран SAX-парсер
                if ("SAX".equalsIgnoreCase(parserType)) {
                    result.append(parseXMLWithSAX(xmlPath));  // Собираем результат парсинга
                } else {
                    result.append("Parser type not supported yet.");
                }

                // Отправка результата клиенту, добавляя символ завершения
                out.println(result);
                out.println("END_OF_RESPONSE");  // Специальное сообщение о завершении
            }
        } catch (IOException e) {
            System.out.println("Error handling client connection: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
            System.out.println("Client disconnected.");
        }
    }


    private String parseXMLWithSAX(String xmlPath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Создаем обработчик для парсинга
            XMLHandler handler = new XMLHandler();

            // Парсим XML по указанному пути
            parser.parse(new File(xmlPath), handler);

            // Возвращаем весь результат парсинга
//            System.out.println(handler.getParsedData());
            return handler.getParsedData();

        } catch (Exception e) {
            return "Error parsing XML: " + e.getMessage();
        }
    }
}
