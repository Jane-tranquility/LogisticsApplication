package entity;



import java.util.Map;



public interface Facility {
	public static final int SPEED = 50;
	public static final int HOURPERDAY = 8;
	public String getFacilityName();
	public int getProcessingRate();
	public int getProcessingFeePerDay();
	public int getShippingFeePerDay();
	public Schedule getSchedule();
	public boolean containsItem(String itemName);
	public Map<String, Integer> getLinks();
	public int getSpeed();
	public int getHourPerDay();
	//public Map<String, Integer> getInventoryMap();
	public Inventory getInventory();
	public void reduceInventory(String itemId, int qtyUsed);
	public void updateSchedule(int orderStartDay, int qty);
	//public int getCurerntProcessingAbility();
	//public int getEarliestAvailableDay();
	public int getProcessingTime(int qty, int orderStartDay);
}
