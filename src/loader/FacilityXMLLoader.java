package loader;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.Facility;
import entity.Inventory;
import entity.Schedule;
import exception.FileInputException;
import factory.FacilityFactory;


public class FacilityXMLLoader implements FacilityLoader{

    @Override
    public Map<String, Facility> loadFacilities(String fileName) {
        Map<String, Facility> map = new HashMap<String, Facility>();

        try {
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < facilityEntries.getLength(); i++) {
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return map;
                }
                Element elem = (Element) facilityEntries.item(i);

                String facilityName = elem.getElementsByTagName("Name").item(0).getTextContent();
                
                String processingRateTemp=elem.getElementsByTagName("ProcessRate").item(0).getTextContent();
                if (processingRateTemp == null || processingRateTemp.length() == 0)
                    throw new FileInputException("No processing rate info for" + facilityName);
                for (int itemp = 0; itemp < processingRateTemp.length(); itemp++) {
                    char chTemp = processingRateTemp.charAt(itemp);
                    if (chTemp - '0' > 9 || chTemp - '0' < 0)
                        throw new FileInputException("invalid processing rate for" + facilityName);
                }
                int processingRate = Integer.parseInt(processingRateTemp);
                
                String processingFeePerDayTemp=elem.getElementsByTagName("ProcessFee").item(0).getTextContent();
                if (processingFeePerDayTemp == null || processingFeePerDayTemp.length() == 0)
                    throw new FileInputException("No processing fee info for" + facilityName);
                for (int itemp = 0; itemp < processingFeePerDayTemp.length(); itemp++) {
                    char chTemp = processingFeePerDayTemp.charAt(itemp);
                    if (chTemp - '0' > 9 || chTemp - '0' < 0)
                        throw new FileInputException("invalid processing fee info for" + facilityName);
                }
                int processingFeePerDay = Integer.parseInt(processingFeePerDayTemp);
                
                String shippingFeePerDayTemp=elem.getElementsByTagName("ShippingFee").item(0).getTextContent();
                if (shippingFeePerDayTemp == null || shippingFeePerDayTemp.length() == 0)
                    throw new FileInputException("No shipping fee info for" + facilityName);
                for (int itemp = 0; itemp < shippingFeePerDayTemp.length(); itemp++) {
                    char chTemp = shippingFeePerDayTemp.charAt(itemp);
                    if (chTemp - '0' > 9 || chTemp - '0' < 0)
                        throw new FileInputException("invalid shipping fee info for" + facilityName);
                }
                int shippingFeePerDay = Integer.parseInt(shippingFeePerDayTemp);
                
                Map<String, Integer> inventoryMap = new HashMap<String, Integer>();
                Map<String, Integer> linkMap = new HashMap<String, Integer>();

                
                NodeList linkList = elem.getElementsByTagName("Link");
                NodeList inventoryList = elem.getElementsByTagName("Inventory");
                for (int j = 0; j < linkList.getLength(); j++) {
                    if (linkList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }
                    entryName = linkList.item(j).getNodeName();
                    if (!entryName.equals("Link")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return map;
                    }
                    elem = (Element) linkList.item(j);
                    String facilityNeighbourName = elem.getElementsByTagName("Name").item(0).getTextContent();
                    
                    String distanceTemp=elem.getElementsByTagName("Distance").item(0).getTextContent();
                    if (distanceTemp == null || distanceTemp.length() == 0)
                        throw new FileInputException("No distance info for" + facilityName + "to " + facilityNeighbourName);
                    for (int itemp = 0; itemp < distanceTemp.length(); itemp++) {
                        char chTemp = distanceTemp.charAt(itemp);
                        if (chTemp - '0' > 9 || chTemp - '0' < 0)
                            throw new FileInputException("invalid distance for" + facilityName + "to " + facilityNeighbourName);
                    }
                    int distance = Integer.parseInt(distanceTemp);
                    linkMap.put(facilityNeighbourName, distance);
                    //System.out.println(facilityNeighbourName + ", " + distance); // for debug
                }
                
                
                //System.out.println(inventoryList.getLength()); // for debug
                for (int k = 0; k < inventoryList.getLength(); k++) {
                    if (inventoryList.item(k).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }
                    
                    entryName = inventoryList.item(k).getNodeName();
                    if (!entryName.equals("Inventory")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return map;
                    }
                    elem = (Element) inventoryList.item(k);
                    String id = elem.getElementsByTagName("Id").item(0).getTextContent();
                    
                    String quantityTemp=elem.getElementsByTagName("Quantity").item(0).getTextContent();
                    if (quantityTemp == null || quantityTemp.length() == 0)
                        throw new FileInputException("No quantity info for" + facilityName + "for"  + id);
                    for (int itemp = 0; itemp < quantityTemp.length(); itemp++) {
                        char chTemp = quantityTemp.charAt(itemp);
                        if (chTemp - '0' > 9 || chTemp - '0' < 0)
                            throw new FileInputException("invalid quantity for" + facilityName + "for " + id);
                    }
                    int quantity = Integer.parseInt(quantityTemp);
                    inventoryMap.put(id, quantity);
                    //System.out.println(id + quantity); // for debug
                }
                Map<Integer, Integer> scheduleMap= new HashMap<>();
                for (int index = 0; index < 35; index++) {
                    scheduleMap.put(index + 1, processingRate);
                }
                Facility facility = FacilityFactory.loadFacility(facilityName, processingRate, processingFeePerDay, shippingFeePerDay, new Inventory(inventoryMap), linkMap, new Schedule(scheduleMap));
                map.put(facilityName, facility);  
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException | FileInputException e) {
            e.printStackTrace();
        }
        return map;
    }

}
