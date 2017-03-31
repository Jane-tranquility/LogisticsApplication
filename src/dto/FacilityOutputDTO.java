package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FacilityOutputDTO {
	public String facilityName;
	public  int processingRate;
	public  int processingFeePerDay;
	//public  int shippingFeePerDay;
	public  Map<String, Double> linkTransScheduleMap;
	public  Map<String, Integer> activieInventoryMap;
	public  Map<String, Integer> depletedInventoryMap;
	public  Map<Integer, Integer> scheduleMap;
	
	public FacilityOutputDTO(String facilityName, int processingRate, int processingFeePerDay,
			Map<String, Double> linkTransScheduleMap, Map<String, Integer> activieInventoryMap,
			Map<String, Integer> depletedInventoryMap, Map<Integer, Integer> scheduleMap) {
		
		this.facilityName = facilityName;
		this.processingRate = processingRate;
		this.processingFeePerDay = processingFeePerDay;
		this.linkTransScheduleMap = linkTransScheduleMap;
		this.activieInventoryMap = activieInventoryMap;
		this.depletedInventoryMap = depletedInventoryMap;
		this.scheduleMap = scheduleMap;
	}

	public void display() {
		
		PriorityQueue<InventoryQuantityPairDTO> activePairs = new PriorityQueue<InventoryQuantityPairDTO>();
		for (String str : activieInventoryMap.keySet())
			activePairs.offer(new InventoryQuantityPairDTO(str, activieInventoryMap.get(str)));
		PriorityQueue<InventoryQuantityPairDTO> depletedPairs = new PriorityQueue<InventoryQuantityPairDTO>();
		for (String str : depletedInventoryMap.keySet())
			depletedPairs.offer(new InventoryQuantityPairDTO(str, depletedInventoryMap.get(str)));
		PriorityQueue<LinkPairDTO> ltsPairs = new PriorityQueue<LinkPairDTO>();
		for (String str : linkTransScheduleMap.keySet())
			ltsPairs.offer(new LinkPairDTO(str, linkTransScheduleMap.get(str)));
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(facilityName);
		System.out.println("-----------");
		System.out.println("Rate per Day:" + processingRate);
		System.out.println("Cost per Day:" + processingFeePerDay);
		System.out.println();
		System.out.println("Direct Links:");
		while (!ltsPairs.isEmpty()) {
			LinkPairDTO lpdto = ltsPairs.poll();
			String neighbor = lpdto.neighborName;
			double days = lpdto.days;
			System.out.print(neighbor + " (");
			System.out.printf("%.1f",days);
			System.out.print("d);");
		}
		
		
		System.out.println("\n");
		System.out.println("Active Inventory:");
		if (activieInventoryMap.isEmpty())
			System.out.print(" None");
		else {
			System.out.println("Item ID\tQuantity");
			//for (String itemName : activieInventoryMap.keySet())
			while (!activePairs.isEmpty()) {
				InventoryQuantityPairDTO temp = activePairs.poll();
				System.out.println(temp.itemName + "\t" + temp.quantity);
			}
				
		}
		System.out.println();
		System.out.print("Depleted (Used-Up) Inventory:");
		if (depletedInventoryMap.isEmpty())
			System.out.print(" None");
		else {
			System.out.println("Item ID\tQuantity");
			//for (String itemName : depletedInventoryMap.keySet())
				//System.out.print(itemName + "\t" + depletedInventoryMap.get(itemName));
			while (!depletedPairs.isEmpty()) {
				InventoryQuantityPairDTO temp = depletedPairs.poll();
				System.out.println(temp.itemName + "\t" + temp.quantity);
			}
		}
		System.out.println("\n");
		System.out.println("Schedule: ");
		System.out.print("Day:       ");
		for (int i = 1; i <= scheduleMap.size(); i++) {
			String day = Integer.toString(i);
			if (day.length() == 1)
				day = " " + day;
			System.out.print(day +  "  ");
		}
			
		System.out.println();
		System.out.print("Available: ");
		for (int i = 1; i <= scheduleMap.size(); i++) {
			String ProcessingAbilityLeft = Integer.toString(scheduleMap.get(i));
			if (ProcessingAbilityLeft.length() == 1)
				ProcessingAbilityLeft = " " + ProcessingAbilityLeft;
			System.out.print(ProcessingAbilityLeft+ "  ");
		}
			
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
	}
}
