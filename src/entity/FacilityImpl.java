package entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.FacilityNetworkDTO;

public class FacilityImpl implements Facility{
	private String facilityName;
	private int processingRate;
	private int processingFeePerDay;
	private int shippingFeePerDay;
	//private Map<String, Integer> inventoryMap;
	private Inventory inventory;
	private Map<String, Integer> linkMap;
	//private Map<Integer, Integer> scheduleMap;
	private Schedule schedule;
	
	
	public FacilityImpl(String facilityName, int processingRate, int processingFeePerDay, int shippingFeePerDay,
			Inventory inventory, Map<String, Integer> linkMap, Schedule schedule) {
		super();
		this.facilityName = facilityName;
		this.processingRate = processingRate;
		this.processingFeePerDay = processingFeePerDay;
		this.shippingFeePerDay = shippingFeePerDay;
		//this.inventoryMap = inventoryMap;
		this.inventory = inventory;
		this.linkMap = linkMap;
		//this.scheduleMap = scheduleMap;
		this.schedule = schedule;
	}



	

	@Override
	public int getProcessingRate() {
		// TODO Auto-generated method stub
		return processingRate;
	}

	@Override
	public int getProcessingFeePerDay() {
		// TODO Auto-generated method stub
		return processingFeePerDay;
	}

	@Override
	public int getShippingFeePerDay() {
		// TODO Auto-generated method stub
		return shippingFeePerDay;
	}
/*
	@Override
	public boolean containsItem(String itemName) {
		return inventoryMap.containsKey(itemName);
	}
*/

	@Override
	public Map<String, Integer> getLinks() {
		return linkMap;
	}

/*
	@Override
	public Map<Integer, Integer> getSchedule() {
		return scheduleMap;
	}
*/




	@Override
	public String getFacilityName() {
		// TODO Auto-generated method stub
		return facilityName;
	}





	


/*
	@Override
	public Map<String, Integer> getInventoryMap() {
		// TODO Auto-generated method stub
		return inventoryMap;
	}
*/




	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return SPEED;
	}





	@Override
	public int getHourPerDay() {
		// TODO Auto-generated method stub
		return HOURPERDAY;
	}





	@Override
	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return schedule;
	}





	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}


	@Override
	public boolean containsItem(String itemName) {
		// TODO Auto-generated method stub
		return false;
	}


	
	/*
	String facilityName = getFacilityName();
	List<FacilityNetworkDTO> list = new ArrayList<FacilityNetworkDTO>();
	list.add(new Faci);
	for (String name : linkMap.keySet()) {
		String facilityNeighbourName =  name;
		int distance = linkMap.get(facilityNeighbourName);
		FacilityNetworkDTO facilityNetworkDTO = new FacilityNetworkDTO(facilityName, distance, facilityNeighbourName);
		
	}
	return null;
	*/
	public void reduceInventory(String itemId, int qtyUsed) {
		inventory.reduceInv(itemId, qtyUsed);
	}
	
	public void updateSchedule(int orderStartDay, int qty) {
		schedule.bookSchedule(orderStartDay, qty);
	}
	/*
	@Override
	public int getCurerntProcessingAbility() {
		
		return schedule.getRemainingProcessAbility();
	}
	public int getEarliestAvailableDay() {
		int psAbility = schedule.getRemainingProcessAbility();
		int days = 0;
		int daysReamining = psAbility / processingRate;
		if (psAbility % processingRate == 0)
			return schedule.size() - daysReamining + 1;
		else
			return schedule.size() - daysReamining;
		
	}
	*/





	@Override
	public int getProcessingTime(int qty, int orderStartDay) {
		
		return schedule.getProcessingTime(qty, orderStartDay);
	}

}
