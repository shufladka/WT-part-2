package by.bsuir.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
    private StringBuilder parsedData = new StringBuilder();
    private String currentElement = "";
    private boolean isInsideWashingMachine = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;

        // Если элемент WashingMachine, добавляем ID
        if (qName.equalsIgnoreCase("WashingMachine")) {
            isInsideWashingMachine = true;
            String id = attributes.getValue("id");
            parsedData.append("\nWashing Machine ID: ").append(id).append("\n");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();

        // Игнорируем пустые значения
        if (value.isEmpty() || !isInsideWashingMachine) {
            return;
        }

        // Обрабатываем каждый элемент внутри WashingMachine
        switch (currentElement) {
            case "brand":
                parsedData.append("Brand: ").append(value).append("\n");
                break;
            case "model":
                parsedData.append("Model: ").append(value).append("\n");
                break;
            case "maxLoad":
                parsedData.append("Max Load (kg): ").append(value).append("\n");
                break;
            case "height":
                parsedData.append("Height (cm): ").append(value).append("\n");
                break;
            case "width":
                parsedData.append("Width (cm): ").append(value).append("\n");
                break;
            case "depth":
                parsedData.append("Depth (cm): ").append(value).append("\n");
                break;
            case "angularVelocity":
                parsedData.append("Angular Velocity (RPM): ").append(value).append("\n");
                break;
            case "amountOfPrograms":
                parsedData.append("Amount of Programs: ").append(value).append("\n");
                break;
            case "isConnectedToPhone":
                parsedData.append("Connected to Phone: ").append(value.equals("true") ? "Yes" : "No").append("\n");
                break;
            case "energyEfficiency":
                parsedData.append("Energy Efficiency: ").append(value).append("\n");
                break;
            case "controlType":
                parsedData.append("Control Type: ").append(value).append("\n");
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("WashingMachine")) {
            isInsideWashingMachine = false;  // Заканчиваем обработку текущей стиральной машины
            parsedData.append("====================================");
        }
        currentElement = "";  // Сбрасываем текущий элемент
    }

    public String getParsedData() {
        return parsedData.toString();
    }
}
