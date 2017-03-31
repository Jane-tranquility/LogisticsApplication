package dto;

import java.util.List;

public class LogisticsRecord {
	public String itemId;
	public int qty;
	public int cost;
	public List<ItemArrivalDetail> iadList;
	public List<LogisticsDetail> ldList;
	public int backOrderNum;
	public LogisticsRecord(String itemId, int qty, int cost, List<ItemArrivalDetail> iadList,
			List<LogisticsDetail> ldList, int backOrderNum) {
		super();
		this.itemId = itemId;
		this.qty = qty;
		this.cost = cost;
		this.iadList = iadList;
		this.ldList = ldList;
		this.backOrderNum = backOrderNum;
	}
	public void display() {
		System.out.println("---");
		System.out.print("Item Id: " + itemId +", Quantity: " + qty +", Total Cost" + cost);
		System.out.println();
		System.out.println("Item Arrivals");
		for (ItemArrivalDetail iad : iadList)
			iad.display();
		System.out.println();
		System.out.println("Logistics Details");
		for (LogisticsDetail ld :ldList)
			ld.display();
		if (backOrderNum != 0)
			System.out.println("Back-order : " + backOrderNum + " more items are needed");
	}
}
