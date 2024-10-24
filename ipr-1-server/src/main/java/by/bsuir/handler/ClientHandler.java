package by.bsuir.handler;

import by.bsuir.domain.WashingMachine;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Класс "Обработчик клиента"
 */
public class ClientHandler implements Runnable {

    /**
     * Объект класса "Сокет"
     */
    private final Socket socket;

    /**
     * Путь к XSD-схеме
     */
    private static final String XSD_PATH = "E:\\IntellijIdeaProjects\\WT-part-2\\ipr-1-server\\src\\main\\resources\\wm.xsd";

    /**
     * Конструктор класса "Обработчик клиента"
     * @param socket Сокет
     */
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Переопределение метода "Запуск" для работы с сокетом
     */
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String xmlPath;
            String parserType;

            // Бесконечный цикл
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

                // Проверяем XML перед отправкой
                validateXml(xmlPath);

                // Обработка запроса
                StringBuilder result = new StringBuilder();

                switch (parserType) {
                    case "SAX":
                        result.append(parseXMLWithSAX(xmlPath));
                        break;
                    case "StAX":
                        result.append(parseXMLWithStAX(xmlPath));
                        break;
                    case "DOM":
                        result.append(parseXMLWithDOM(xmlPath));
                        break;
                    default:
                        result.append("Parser type not supported yet.");
                        break;
                }

                // Отправка результата клиенту
                out.println(result);

                // Добавляем сообщение о завершении передачи
                out.println("END_OF_RESPONSE");
            }
        } catch (IOException e) {
            System.out.println("Error handling client connection: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while validating XML: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
            System.out.println("Client disconnected.");
        }
    }

    /**
     * Метод для парсинга XML-документа парсером SAX
     * @param xmlPath Путь к XML-документу
     * @return List of washing machines
     */
    private List<WashingMachine> parseXMLWithSAX(String xmlPath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Создаем обработчик для парсинга
            SAXHandler handler = new SAXHandler();

            // Парсим XML по указанному пути
            parser.parse(new File(xmlPath), handler);

            // Возвращаем весь результат парсинга
            return handler.getWashingMachines();

        } catch (Exception e) {
            System.out.println("Error parsing XML: " + e.getMessage());
        }

        return null;
    }

    /**
     * Метод для парсинга XML-документа парсером StAX
     * @param xmlPath Путь к XML-документу
     * @return List of washing machines
     */
    private List<WashingMachine> parseXMLWithStAX(String xmlPath) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(xmlPath));

            // Создаем обработчик для парсинга
            StAXHandler handler = new StAXHandler();

            List<WashingMachine> washingMachines = handler.processXml(reader);
            reader.close();

            // Возвращаем весь результат парсинга
            return washingMachines;

        } catch (Exception e) {
            System.out.println("Error parsing XML: " + e.getMessage());
        }

        return null;
    }

    /**
     * Метод для парсинга XML-документа парсером DOM
     * @param xmlPath Путь к XML-документу
     * @return List of washing machines
     */
    public List<WashingMachine> parseXMLWithDOM(String xmlPath) {
        try {

            // Создаем обработчик для парсинга
            DOMHandler handler = new DOMHandler();

            Document doc = handler.createDocument(xmlPath);
            NodeList nodeList = doc.getElementsByTagName("WashingMachine");

            // Возвращаем весь результат парсинга
            return handler.processNodeList(nodeList);

        } catch (Exception e) {
            System.out.println("Error parsing XML: " + e.getMessage());
        }
        return null;
    }

    /**
     * Метод для валидации XML-документа по XSD-схеме
     * @param xmlFilePath Путь к XML-документу
     * @throws Exception Прокидывание всех ошибок
     */
    private void validateXml(String xmlFilePath) throws Exception {

        // Создаем фабрику схем
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(XSD_PATH));

        // Создаем валидатор
        Validator validator = schema.newValidator();

        // Выполняем проверку
        validator.validate(new StreamSource(new File(xmlFilePath)));
    }
}
