package by.bsuir.handler;

import by.bsuir.domain.ControlType;
import by.bsuir.domain.Dimensions;
import by.bsuir.domain.EnergyEfficiency;
import by.bsuir.domain.WashingMachine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

                // Если выбран SAX-парсер
                if ("DOM".equalsIgnoreCase(parserType)) {
                    result.append(parseXMLWithDOM(xmlPath));  // Собираем результат парсинга
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

    public List<WashingMachine> parseXMLWithDOM(String xmlPath) {
        List<WashingMachine> washingMachines = new ArrayList<>();

        try {
            // Создание документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xmlPath));

            // Нормализация XML структуры
            doc.getDocumentElement().normalize();

            // Получаем список всех узлов WashingMachine
            NodeList nodeList = doc.getElementsByTagName("WashingMachine");

            // Проходим по каждому узлу WashingMachine
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Извлекаем данные из каждого элемента
                    int id = Integer.parseInt(element.getAttribute("id"));
                    String brand = getElementTextContent(element, "brand");
                    String model = getElementTextContent(element, "model");
                    double maxLoad = Double.parseDouble(getElementTextContent(element, "maxLoad"));

                    // Извлекаем вложенный элемент dimensions
                    Element dimensionsElement = (Element) element.getElementsByTagName("dimensions").item(0);
                    double height = Double.parseDouble(getElementTextContent(dimensionsElement, "height"));
                    double width = Double.parseDouble(getElementTextContent(dimensionsElement, "width"));
                    double depth = Double.parseDouble(getElementTextContent(dimensionsElement, "depth"));
                    double weight = Double.parseDouble(getElementTextContent(dimensionsElement, "weight"));

                    Dimensions dimensions = new Dimensions(height, width, depth, weight);

                    int angularVelocity = Integer.parseInt(Objects.requireNonNull(getElementTextContent(element, "angularVelocity")));
                    int amountOfPrograms = Integer.parseInt(getElementTextContent(element, "amountOfPrograms"));
                    boolean isConnectedToPhone = Boolean.parseBoolean(getElementTextContent(element, "isConnectedToPhone"));
                    EnergyEfficiency energyEfficiency = EnergyEfficiency.valueOf(getElementTextContent(element, "energyEfficiency"));
                    ControlType controlType = ControlType.valueOf(getElementTextContent(element, "controlType"));

                    // Создаем объект WashingMachine и добавляем его в список
                    WashingMachine washingMachine = new WashingMachine(id, brand, model, maxLoad, dimensions, angularVelocity, amountOfPrograms, isConnectedToPhone, energyEfficiency, controlType);
                    washingMachines.add(washingMachine);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing XML: " + e.getMessage());
        }

        return washingMachines;
    }

    // Вспомогательный метод для получения текста элемента
    private String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
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
