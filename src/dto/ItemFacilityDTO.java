package dto;

import java.util.List;

public class ItemFacilityDTO {
	public String itemName;
	public List<String> facilitiesName;
	public ItemFacilityDTO(String itemName, List<String> facilitiesName) {
		
		this.itemName = itemName;
		this.facilitiesName = facilitiesName;
	}
	
	public void display() {
		System.out.print("{itemName: " + itemName + "< ");
		for (String facilityName : facilitiesName)
			System.out.print(facilityName + " ");
		System.out.print(" > }");
	}
}
