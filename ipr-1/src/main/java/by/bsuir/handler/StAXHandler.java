package by.bsuir.handler;

import by.bsuir.domain.ControlType;
import by.bsuir.domain.Dimensions;
import by.bsuir.domain.EnergyEfficiency;
import by.bsuir.domain.WashingMachine;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StAXHandler {

    public List<WashingMachine> processXml(XMLStreamReader reader) throws Exception {
        List<WashingMachine> washingMachines = new ArrayList<>();
        WashingMachine washingMachine = null;
        Dimensions dimensions = null;
        String currentElement = "";

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    currentElement = reader.getLocalName();
                    if ("WashingMachine".equals(currentElement)) {
                        washingMachine = createWashingMachine(reader);
                    } else if ("dimensions".equals(currentElement)) {
                        dimensions = new Dimensions();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    processCharacterData(reader, washingMachine, dimensions, currentElement);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if ("WashingMachine".equals(reader.getLocalName()) && washingMachine != null) {
                        washingMachine.setDimensions(dimensions);
                        washingMachines.add(washingMachine);
                    }
                    break;
            }
        }

        return washingMachines;
    }

    private WashingMachine createWashingMachine(XMLStreamReader reader) {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        return washingMachine;
    }

    private void processCharacterData(XMLStreamReader reader, WashingMachine washingMachine, Dimensions dimensions, String currentElement) {
        String data = reader.getText().trim();
        if (washingMachine != null && !data.isEmpty()) {
            switch (currentElement) {
                case "brand":
                    washingMachine.setBrand(data);
                    break;
                case "model":
                    washingMachine.setModel(data);
                    break;
                case "maxLoad":
                    washingMachine.setMaxLoad(Double.parseDouble(data));
                    break;
                case "height":
                    dimensions.setHeight(Double.parseDouble(data));
                    break;
                case "width":
                    dimensions.setWidth(Double.parseDouble(data));
                    break;
                case "depth":
                    dimensions.setDepth(Double.parseDouble(data));
                    break;
                case "weight":
                    dimensions.setWeight(Double.parseDouble(data));
                    break;
                case "angularVelocity":
                    washingMachine.setAngularVelocity(Integer.parseInt(data));
                    break;
                case "amountOfPrograms":
                    washingMachine.setAmountOfPrograms(Integer.parseInt(data));
                    break;
                case "isConnectedToPhone":
                    washingMachine.setConnectedToPhone(Boolean.parseBoolean(data));
                    break;
                case "energyEfficiency":
                    washingMachine.setEnergyEfficiency(EnergyEfficiency.valueOf(data));
                    break;
                case "controlType":
                    washingMachine.setControlType(ControlType.valueOf(data));
                    break;
            }
        }
    }
}
