package loader;


import java.util.Map;

import entity.Item;


public interface ItemLoader {
	/* old version
	public ItemDTO[] getItemsCost(String[] itemsName);
	public boolean verifyItems(String[] itemsName);
	*/
	public Map<String, Item> loadItems(String fileName);
	
	
	
}
