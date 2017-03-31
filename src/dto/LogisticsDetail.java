package dto;

public class LogisticsDetail {
	public String facilityName;
	public int processNum;
	public int totalQty;
	public int cost;
	public int processStartDay;
	public int processEndDay;
	public int travelStartDay;
	public int travelEndDay;
	public LogisticsDetail(String facilityName, int processNum, int totalQty, int cost, int processStartDay,
			int processEndDay, int travelStartDay, int travelEndDay) {
		super();
		this.facilityName = facilityName;
		this.processNum = processNum;
		this.totalQty = totalQty;
		this.cost = cost;
		this.processStartDay = processStartDay;
		this.processEndDay = processEndDay;
		this.travelStartDay = travelStartDay;
		this.travelEndDay = travelEndDay;
	}
	
	public void display() {
		System.out.println("Name :" + facilityName + " ( " + processNum + "of" + totalQty + " )");
		System.out.println("Cost: $" + cost);
		System.out.println("Processing Start: Day" + processStartDay);
		System.out.println("Processing End: Day" + processEndDay);
		System.out.println("Travel Start: Day" + travelStartDay);
		System.out.println("Travel End: Day" + travelEndDay);
		System.out.println();
		
	}
	
}
