package dto;

import java.util.List;

public class LowPathDTO {
	public String facilityNameRequest;
	public String facilityNamewithStock;
	public List<String> facilitiesNamesInPath;
	public int distance;
	public int speed;
	public int hourPerDay;
	
	
	public LowPathDTO(String facilityNameRequest, String facilityNamewithStock, List<String> facilitiesNamesInPath,
			int distance, int speed, int hourPerDay) {
		super();
		this.facilityNameRequest = facilityNameRequest;
		this.facilityNamewithStock = facilityNamewithStock;
		this.facilitiesNamesInPath = facilitiesNamesInPath;
		this.distance = distance;
		this.speed = speed;
		this.hourPerDay = hourPerDay;
	}


	public void display() {
		double days =  (double)distance / (double)(hourPerDay * speed);
		System.out.println(facilityNamewithStock + " to " + facilityNameRequest + ": ");
		System.out.print("     -");
		for (int i = 0; i < facilitiesNamesInPath.size() - 1; i++) {
			System.out.print(facilitiesNamesInPath.get(i) + " -> ");
		}
		System.out.println(facilitiesNamesInPath.get(facilitiesNamesInPath.size() - 1) + " = " + distance + " mi");
		System.out.print("     -" + distance + " mi / (" + hourPerDay  + " hours per day * " + speed + " mph ) = ");
		System.out.printf("%.2f days",days);
		System.out.println("\n");
		
	}

}
