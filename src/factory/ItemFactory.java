package factory;

import entity.ItemImpl;
import entity.Item;

public class ItemFactory implements Factory{
	
	public static Item loadItem(String id, int price) {
		return new ItemImpl(id, price);
	}
}
