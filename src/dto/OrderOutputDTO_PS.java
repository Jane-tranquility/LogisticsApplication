package dto;

public class OrderOutputDTO_PS {
	
	
	
	public String itemId;
	public int qty;
	public int cost;
	public int sused;
	public int firstDDay;
	public int lastDDay;
	public int backOrder;
	public OrderOutputDTO_PS(String itemId, int qty, int cost, int sused, int firstDDay, int lastDDay, int backOrder) {
		super();
		this.itemId = itemId;
		this.qty = qty;
		this.cost = cost;
		this.sused = sused;
		this.firstDDay = firstDDay;
		this.lastDDay = lastDDay;
		this.backOrder = backOrder;
	}
	
	
	
	
}
