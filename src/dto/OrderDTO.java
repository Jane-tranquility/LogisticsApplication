package dto;

import java.util.Map;
import java.util.Set;

public class OrderDTO {
	public String orderId;
	public int startTime;
	public String destName;
	public Map<String, Integer> itemMap_order;
	public OrderDTO(String orderId, int startTime, String destName, Map<String, Integer> itemMap_order) {
		//super();
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
	public void display() {
		System.out.print("OrderDTO [orderId=" + orderId + ", startTime=" + startTime + ", destName=" + destName
				+ ", itemMap_order=" + itemMap_order + "]");
		System.out.println();
	}
	
	
}
