package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.ItemDTO;
import entity.Item;
import factory.ItemFactory;
import factory.ItemLoaderFactory;
import loader.ItemLoader;

public class ItemService {
	private Map<String, Item> itemMap = new HashMap<String, Item>();
	private static ItemLoader itemLoader = ItemLoaderFactory.loadItemLoader("XML");
	private volatile static ItemService instance;
	private ItemService() {
		itemMap = itemLoader.loadItems("ItemXML.xml");
	}
	public static ItemService getInstance() {
		if (instance == null) {
			synchronized (ItemService.class) {
				if (instance == null) {
					instance = new ItemService();
				}
			}
		}
		return instance;
	}
	public boolean verifyItems(String[] itemsName) {
		for (String itemName : itemsName) {
			if (!itemMap.containsKey(itemName))
				return false;
		}
		return true;
	}
	public boolean verifyItem(String itemId) {
		return itemMap.containsKey(itemId);
	}

	public ItemDTO[] getItemsCost(String[] itemsName) {
		ItemDTO[] itemDTOs= new ItemDTO[itemsName.length];
		for (int i = 0; i < itemsName.length; i++) {
			int price = itemMap.get(itemsName[i]).getPrice();
			itemDTOs[i] = new ItemDTO(itemsName[i], price);
		}
		return itemDTOs;
	}
	public ItemDTO[] getItemsOutput() {
		String[] itemsName = new String[itemMap.size()];
		List<String> itemsNameList = new ArrayList<String>();
		for (String str : itemMap.keySet()) {
			itemsNameList.add(str);
		}
		for (int i = 0; i < itemsName.length; i++) {
			itemsName[i] = itemsNameList.get(i);
		}
		return getItemsCost(itemsName);	
	}
	
	public int getItemCostByItemId(String itemId) {
		return itemMap.get(itemId).getPrice();
	}
	
}







/* old version: to be delete
private volatile static ItemService instance;
public static ItemService getInstance() {
	if (instance == null) {
		synchronized (ItemService.class) {
			if (instance == null)
				instance = new ItemService();
		}
	}
	return instance;
}
private static ItemLoader itemLoader = ItemLoaderFactory.loadItemLoader("XML");
//private Map<String, Item> items = ItemFactory.loadItem();

 */



