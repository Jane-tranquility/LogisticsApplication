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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dto.ItemDTO;
import entity.Item;
import exception.FileInputException;
import factory.ItemFactory;

public class ItemXMLLoader implements ItemLoader{
	@Override
	public Map<String, Item> loadItems(String fileName)  {
		Map<String, Item> map = new HashMap<String, Item>();
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

            NodeList storeEntries = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < storeEntries.getLength(); i++) {
                if (storeEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = storeEntries.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return map;
                }
                Element elem = (Element) storeEntries.item(i);
                String itemId = elem.getElementsByTagName("Id").item(0).getTextContent();
                
                String temp = elem.getElementsByTagName("Price").item(0).getTextContent();
                if (temp == null || temp.length() == 0)
                    throw new FileInputException("No price info for" + itemId);
                for (int itemp = 0; itemp < temp.length(); itemp++) {
                    char chTemp = temp.charAt(itemp);
                    if (chTemp - '0' > 9 || chTemp - '0' < 0)
                        throw new FileInputException("invalid price for" + itemId);
                }
                
                 int price = Integer.parseInt(temp);
                
                //int price = Integer.parseInt(elem.getElementsByTagName("Price").item(0).getTextContent());
                Item item = ItemFactory.loadItem(itemId, price);
                map.put(itemId, item);
               
            }
		} catch (ParserConfigurationException | SAXException | IOException | DOMException | FileInputException e) {
			e.printStackTrace();
		}
		return map;
	}
}


/*old version : to be deleted
public ItemXMLLoader() {
	
}
*/
