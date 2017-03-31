package loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


import entity.Order;

import exception.FileInputException;

import factory.OrderFacory;

public class OrderXMLLoader implements OrderLoader{

	@Override
	public List<Order> loadOrders(String fileName) {
		//Map<String, Order> orderMap = new HashMap<String, Order>();
		List<Order> orderList = new ArrayList<Order>();
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
	                if (!entryName.equals("Order")) {
	                    System.err.println("Unexpected node found: " + entryName);
	                    return orderList;
	                }
	                Element elem = (Element) facilityEntries.item(i);

	                String orderId = elem.getElementsByTagName("Id").item(0).getTextContent();
	                
	                String startTimeTemp=elem.getElementsByTagName("Sttime").item(0).getTextContent();
	                if (orderId == null || orderId.length() == 0)
	                    throw new FileInputException("order without orderId");
	                for (int itemp = 0; itemp < startTimeTemp.length(); itemp++) {
	                    char chTemp = startTimeTemp.charAt(itemp);
	                    if (chTemp - '0' > 9 || chTemp - '0' < 0)
	                        throw new FileInputException("invalid processing rate for " + orderId);
	                }
	                int startTime = Integer.parseInt(startTimeTemp);
	                String destName = elem.getElementsByTagName("Dest").item(0).getTextContent();
	                if (destName == null || destName.length() == 0)
	                    throw new FileInputException("no dest info for " + orderId);
	                
	                Map<String, Integer> itemMap_order = new HashMap<String, Integer>();
	                NodeList ItemList = elem.getElementsByTagName("Item");
	                
	                
	                for (int j = 0; j < ItemList.getLength(); j++) {
	                    if (ItemList.item(j).getNodeType() == Node.TEXT_NODE) {
	                        continue;
	                    }
	                    entryName = ItemList.item(j).getNodeName();
	                    if (!entryName.equals("Item")) {
	                        System.err.println("Unexpected node found: " + entryName);
	                        return orderList;
	                    }
	                    elem = (Element) ItemList.item(j);
	                    String itemId = elem.getElementsByTagName("Id").item(0).getTextContent();
	                    
	                    String qtyTemp=elem.getElementsByTagName("Qty").item(0).getTextContent();
	                    if (itemId == null || itemId.length() == 0)
	                        throw new FileInputException("No itemId info for certain item " + orderId);
	                    for (int itemp = 0; itemp < qtyTemp.length(); itemp++) {
	                        char chTemp = qtyTemp.charAt(itemp);
	                        if (chTemp - '0' > 9 || chTemp - '0' < 0)
	                            throw new FileInputException("invalid quantity info for " + itemId + "in " + orderId);
	                    }
	                    int qty = Integer.parseInt(qtyTemp);
	                    itemMap_order.put(itemId, qty);
	                    //System.out.println(facilityNeighbourName + ", " + distance); // for debug
	                }
	                Order order = OrderFacory.loadOrder(orderId, startTime, destName, itemMap_order);
	                //orderMap.put(orderId, order);
	                orderList.add(order);
	            }
	        } catch (ParserConfigurationException | SAXException | IOException | DOMException | FileInputException e) {
	            e.printStackTrace();
	        }
	        return orderList;
	}

}
