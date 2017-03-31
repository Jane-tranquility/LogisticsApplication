package factory;


import java.util.Map;

import entity.Facility;
import entity.FacilityImpl;
import entity.Inventory;
import entity.Schedule;



public class FacilityFactory implements Factory{
	public static Facility loadFacility(String facilityName, int processingRate, int processingFeePerDay, int shippingFeePerDay,
			Inventory inventory, Map<String, Integer> linkMap, Schedule schedule) {
		return new FacilityImpl(facilityName, processingRate, processingFeePerDay, shippingFeePerDay, inventory, linkMap, schedule);
	}
}
