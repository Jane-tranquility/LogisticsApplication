package loader;

import java.util.Map;

import dto.ItemDTO;
import entity.Item;

public class ItemNullLoader implements ItemLoader{

	@Override
	public Map<String, Item> loadItems(String fileName) {

		return null;
	}
}
