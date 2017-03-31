package dto;

public class FacilityNetworkDTO {
	public String facilityName;
	public int distance;
	public String facilityNeighbourName;
	public int speed;
	public int hourPerDay;

	
	public FacilityNetworkDTO(String facilityName, int distance, String facilityNeighbourName, int speed,
			int hourPerDay) {
		super();
		this.facilityName = facilityName;
		this.distance = distance;
		this.facilityNeighbourName = facilityNeighbourName;
		this.speed = speed;
		this.hourPerDay = hourPerDay;
	}


	//@Override
	public void display() {
		System.out.println("FacilityNetworkDTO [facilityName=" + facilityName + ", distance=" + distance
				+ ", facilityNeighbourName=" + facilityNeighbourName + ", speed=" + speed + ", hourPerDay=" + hourPerDay
				+ "]");
	}


	
}
