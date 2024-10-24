package by.bsuir.handler;

import by.bsuir.domain.ControlType;
import by.bsuir.domain.Dimensions;
import by.bsuir.domain.EnergyEfficiency;
import by.bsuir.domain.WashingMachine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс "Обработчик DOM-парсера"
 */
public class DOMHandler {

    /**
     * Метод для создания и нормализации XML-документа
     * @param xmlPath Путь к XML-документу
     * @return Document
     * @throws Exception Прокидывание всех ошибок
     */
    public Document createDocument(String xmlPath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlPath));

        // Нормализация структуры
        doc.getDocumentElement().normalize();
        return doc;
    }

    /**
     * Метод для обработки списка узлов
     * @param nodeList Список узлов
     * @return List of washing machines
     */
    public List<WashingMachine> processNodeList(NodeList nodeList) {
        List<WashingMachine> washingMachines = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                WashingMachine washingMachine = processWashingMachineElement((Element) node);
                washingMachines.add(washingMachine);
            }
        }
        return washingMachines;
    }

    /**
     * Метод для обработки элементов (полей) стиральной машины
     * @param element Элемент
     * @return WashingMachine
     */
    private WashingMachine processWashingMachineElement(Element element) {
        int id = Integer.parseInt(element.getAttribute("id"));
        String brand = getElementTextContent(element, "brand");
        String model = getElementTextContent(element, "model");
        double maxLoad = Double.parseDouble(Objects.requireNonNull(getElementTextContent(element, "maxLoad")));

        Dimensions dimensions = extractDimensions(element);
        int angularVelocity = Integer.parseInt(Objects.requireNonNull(getElementTextContent(element, "angularVelocity")));
        int amountOfPrograms = Integer.parseInt(Objects.requireNonNull(getElementTextContent(element, "amountOfPrograms")));
        boolean isConnectedToPhone = Boolean.parseBoolean(getElementTextContent(element, "isConnectedToPhone"));
        EnergyEfficiency energyEfficiency = EnergyEfficiency.valueOf(getElementTextContent(element, "energyEfficiency"));
        ControlType controlType = ControlType.valueOf(getElementTextContent(element, "controlType"));

        return new WashingMachine(id, brand, model, maxLoad, dimensions,
                angularVelocity, amountOfPrograms, isConnectedToPhone, energyEfficiency, controlType);
    }

    /**
     * Метод для извлечения элементов габаритов стиральной машины
     * @param element Элемент
     * @return Dimensions
     */
    private Dimensions extractDimensions(Element element) {
        Element dimensionsElement = (Element) element.getElementsByTagName("dimensions").item(0);
        double height = Double.parseDouble(Objects.requireNonNull(getElementTextContent(dimensionsElement, "height")));
        double width = Double.parseDouble(Objects.requireNonNull(getElementTextContent(dimensionsElement, "width")));
        double depth = Double.parseDouble(Objects.requireNonNull(getElementTextContent(dimensionsElement, "depth")));
        double weight = Double.parseDouble(Objects.requireNonNull(getElementTextContent(dimensionsElement, "weight")));
        return new Dimensions(width, height, depth, weight);
    }

    /**
     * Вспомогательный метод для получения текста элемента по тегу
     * @param parent Родительский элемент
     * @param tagName Имя тега
     * @return String
     */
    private String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}
