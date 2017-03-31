package dto;

import java.util.List;
import java.util.Map;

public class OrderOutputDTO {
	public String orderId;
	public int startTime;
	public String destName;
	public Map<String, Integer> itemMap_order;
	
	public int cost;
	public int firstDivDay;
	public int lastDivDay;
	
	public List<OrderOutputDTO_PS> psList;

	public OrderOutputDTO(String orderId, int startTime, String destName, Map<String, Integer> itemMap_order, int cost,
			int firstDivDay, int lastDivDay, List<OrderOutputDTO_PS> psList) {
		super();
		this.orderId = orderId;
		this.startTime = startTime;
		this.destName = destName;
		this.itemMap_order = itemMap_order;
		this.cost = cost;
		this.firstDivDay = firstDivDay;
		this.lastDivDay = lastDivDay;
		this.psList = psList;
	}

	
	
	
	
}
