package factory;

import java.util.Map;

import entity.Order;
import entity.OrderImpl;

public class OrderFacory {
	public static Order loadOrder(String orderId, int startTime, String destName, Map<String, Integer> itemMap_order) {
		return new OrderImpl(orderId, startTime, destName, itemMap_order);
	}
}
