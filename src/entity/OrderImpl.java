package entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderImpl implements Order{
	private String orderId;
	private int startTime;
	private String destName;
	private Map<String, Integer> itemMap_order;
	
	
	
	public OrderImpl(String orderId, int startTime, String destName, Map<String, Integer> itemMap_order) {
		super();
		this.orderId = orderId;
		this.startTime = startTime;
		this.destName = destName;
		this.itemMap_order = itemMap_order;
	}

	public String getOrderId() {
		return orderId;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public String getDestName() {
		return destName;
	}
	
	public Set<String> keySet() {
		return itemMap_order.keySet();
	}
	public int get(String itemId) {
		return itemMap_order.get(itemId);
	}
}
