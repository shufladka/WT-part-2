package by.bsuir.handler;

import by.bsuir.domain.Dimensions;
import by.bsuir.domain.EnergyEfficiency;
import by.bsuir.domain.ControlType;
import by.bsuir.domain.WashingMachine;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
    private final List<WashingMachine> washingMachines = new ArrayList<>();
    private WashingMachine currentWashingMachine;
    private String currentElement = "";
    private boolean isInsideWashingMachine = false;

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

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("WashingMachine")) {
            isInsideWashingMachine = false; // Заканчиваем обработку текущей стиральной машины
            washingMachines.add(currentWashingMachine); // Добавляем объект в коллекцию
        }
        currentElement = ""; // Сбрасываем текущий элемент
    }

    public List<WashingMachine> getWashingMachines() {
        return washingMachines;
    }
}
