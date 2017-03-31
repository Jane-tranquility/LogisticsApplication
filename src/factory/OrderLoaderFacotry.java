package factory;

import loader.OrderLoader;
import loader.OrderNullLoader;
import loader.OrderXMLLoader;

public class OrderLoaderFacotry {
	public static OrderLoader loadOrderLoader(String loadFormat) {
		if ("XML".equals(loadFormat))
			return new OrderXMLLoader();
		else
			return new OrderNullLoader();
	}
}
