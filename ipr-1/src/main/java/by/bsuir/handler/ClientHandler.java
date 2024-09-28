package by.bsuir.handler;

import by.bsuir.domain.WashingMachine;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private static final String XSD_PATH = "E:\\IntellijIdeaProjects\\WT-part-2\\ipr-1\\src\\main\\resources\\wm.xsd";

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

    private List<WashingMachine> parseXMLWithSAX(String xmlPath) {
        try {

            // Проверяем XML перед отправкой
            validateXml(xmlPath);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Создаем обработчик для парсинга
            XMLHandler handler = new XMLHandler();

            // Парсим XML по указанному пути
            parser.parse(new File(xmlPath), handler);

            // Возвращаем весь результат парсинга
            return handler.getWashingMachines();

        } catch (Exception e) {
            System.out.println("Error parsing XML: " + e.getMessage());
        }

        return null;
    }

    public static void validateXml(String xmlFilePath) throws Exception {

        // Создаем фабрику схем
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(XSD_PATH));

        // Создаем валидатор
        Validator validator = schema.newValidator();

        // Выполняем проверку
        validator.validate(new StreamSource(new File(xmlFilePath)));
    }
}
