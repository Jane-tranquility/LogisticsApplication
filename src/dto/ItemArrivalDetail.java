package dto;

public class ItemArrivalDetail {
	public int day;
	public int qty;
	public double pct;
	public double accPct;
	public ItemArrivalDetail(int day, int qty, double pct, double accPct) {
		super();
		this.day = day;
		this.qty = qty;
		this.pct = pct;
		this.accPct = accPct;
	}
	public void display() {
		System.out.print("Day " + day +": " + qty + " ( ");   //+ qty + " " + pct +" " + accPct);
		System.out.printf("%.2f", pct * 100);
		System.out.print("%, ");
		System.out.printf("%.2f", accPct * 100);
		System.out.print("% of total)");
		System.out.println();
	}
	
}
