package loader;


import java.util.List;
import java.util.Map;

import entity.Order;

public class OrderNullLoader implements OrderLoader{

	@Override
	public List<Order> loadOrders(String fileName) {
		
		return null;
	}
	
}
