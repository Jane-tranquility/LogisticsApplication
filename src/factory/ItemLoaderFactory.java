package factory;

import loader.ItemLoader;
import loader.ItemNullLoader;
import loader.ItemXMLLoader;

public class ItemLoaderFactory implements Factory{
	//private static ItemLoader itemLoader;
	public static ItemLoader loadItemLoader(String loadFormat) {
		if ("XML".equals(loadFormat))
			return new ItemXMLLoader();
		else
			return new ItemNullLoader();
	}
}
