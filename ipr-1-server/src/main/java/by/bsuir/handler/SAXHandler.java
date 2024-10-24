package by.bsuir.handler;

import by.bsuir.domain.ControlType;
import by.bsuir.domain.Dimensions;
import by.bsuir.domain.EnergyEfficiency;
import by.bsuir.domain.WashingMachine;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс "Обработчик SAX-парсера"
 */
public class SAXHandler extends DefaultHandler {

    /**
     * Список стиральных машин
     */
    private final List<WashingMachine> washingMachines = new ArrayList<>();

    /**
     * Текущая стиральная машина
     */
    private WashingMachine currentWashingMachine;

    /**
     * Текущий элемент
     */
    private String currentElement = "";

    /**
     * Мы внутри структуры стиральной машины
     */
    private boolean isInsideWashingMachine = false;

    /**
     * Метод для получения начального элемента
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;

        if (qName.equalsIgnoreCase("WashingMachine")) {
            isInsideWashingMachine = true;

            // Создаем новый объект WashingMachine с ID
            int id = Integer.parseInt(attributes.getValue("id"));
            currentWashingMachine = new WashingMachine(id, null, null, null, null, 0, 0, false, null, null);
        }
    }

    /**
     * Метод для получения символов
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        // Игнорируем пустые значения
        if (value.isEmpty() || !isInsideWashingMachine) {
            return;
        }

        // Обрабатываем каждый элемент внутри WashingMachine
        switch (currentElement) {
            case "brand":
                currentWashingMachine.setBrand(value);
                break;
            case "model":
                currentWashingMachine.setModel(value);
                break;
            case "maxLoad":
                currentWashingMachine.setMaxLoad(Double.parseDouble(value));
                break;
            case "height":
                if (currentWashingMachine.getDimensions() == null) {
                    currentWashingMachine.setDimensions(new Dimensions());
                }
                currentWashingMachine.getDimensions().setHeight(Double.parseDouble(value));
                break;
            case "width":
                if (currentWashingMachine.getDimensions() == null) {
                    currentWashingMachine.setDimensions(new Dimensions());
                }
                currentWashingMachine.getDimensions().setWidth(Double.parseDouble(value));
                break;
            case "depth":
                if (currentWashingMachine.getDimensions() == null) {
                    currentWashingMachine.setDimensions(new Dimensions());
                }
                currentWashingMachine.getDimensions().setDepth(Double.parseDouble(value));
                break;
            case "weight":
                if (currentWashingMachine.getDimensions() == null) {
                    currentWashingMachine.setDimensions(new Dimensions());
                }
                currentWashingMachine.getDimensions().setWeight(Double.parseDouble(value));
                break;
            case "angularVelocity":
                currentWashingMachine.setAngularVelocity(Integer.parseInt(value));
                break;
            case "amountOfPrograms":
                currentWashingMachine.setAmountOfPrograms(Integer.parseInt(value));
                break;
            case "isConnectedToPhone":
                currentWashingMachine.setConnectedToPhone(Boolean.parseBoolean(value));
                break;
            case "energyEfficiency":
                currentWashingMachine.setEnergyEfficiency(EnergyEfficiency.valueOf(value));
                break;
            case "controlType":
                currentWashingMachine.setControlType(ControlType.valueOf(value));
                break;
            default:
                break;
        }
    }

    /**
     * Метод для получения последнего элемента
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("WashingMachine")) {

            // Заканчиваем обработку текущей стиральной машины
            isInsideWashingMachine = false;

            // Добавляем объект в коллекцию
            washingMachines.add(currentWashingMachine);
        }

        // Сбрасываем текущий элемент
        currentElement = "";
    }

    /**
     * Метод для получения списка стиральных машин
     * @return ist of washing machines
     */
    public List<WashingMachine> getWashingMachines() {
        return washingMachines;
    }
}
