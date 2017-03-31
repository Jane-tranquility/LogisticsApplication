package entity;

public class FacilityRecord {
	private String facilityName;
	private int qty;
	//private int startDay;
	private int endDay;
	private int travelTime;
	private int arrivalDay;
	public FacilityRecord(String facilityName, int qty, int endDay, int travelTime, int arrivalDay) {
		super();
		this.facilityName = facilityName;
		this.qty = qty;
		//this.startDay = startDay;
		this.endDay = endDay;
		this.travelTime = travelTime;
		this.arrivalDay = arrivalDay;
	}
	public int getArrivalDay() {
		return arrivalDay;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public int getQty() {
		return qty;
	}
	public int getEndDay() {
		return endDay;
	}
	public int getTravelTime() {
		return travelTime;
	}
	public void setQty(int updatedQty) {
		this.qty = updatedQty;
	} 
	public void update(int updatedEndProcessingDay, int updatedArrivalTime) {
		this.endDay = updatedEndProcessingDay;
		this.arrivalDay = updatedArrivalTime;
	}
	/*
	public int getStartDay() {
		return startDay;
	}
	*/
	
}
