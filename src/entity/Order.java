package entity;

import java.util.Set;

public interface Order {
	
	public String getOrderId();
	
	public int getStartTime();
	
	public String getDestName();
	
	public Set<String> keySet();
	public int get(String itemId);
}
