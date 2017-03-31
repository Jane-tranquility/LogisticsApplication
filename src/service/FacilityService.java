package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.FacilityNetworkDTO;
import dto.FacilityOutputDTO;
import dto.ItemFacilityDTO;
import entity.Facility;
import entity.Item;
import factory.FacilityLoaderFactory;
import loader.FacilityLoader;

public class FacilityService {
	private Map<String, Facility>  facilityMap = new HashMap<String, Facility>();
	private static FacilityLoader facilityLoader = FacilityLoaderFactory.loadFacilityLoader("XML");
	private volatile static FacilityService instance;
	private FacilityService() {
		facilityMap = facilityLoader.loadFacilities("FacilityNetworkXML.xml");
	}
	public static FacilityService getInstance() {
		if (instance == null) {
			synchronized (ItemService.class) {
				if (instance == null) {
					instance = new FacilityService();
				}
			}
		}
		return instance;
	}
	public ItemFacilityDTO[] getFacilitiesByItemNames(String[] itemNames) {
		ItemFacilityDTO[] itemFacilityDTOs = new ItemFacilityDTO[itemNames.length];
		for (int i = 0; i < itemFacilityDTOs.length; i++) {
			List<String> facilityNames = new ArrayList<String>();
			for (String facilityName : facilityMap.keySet()) {
				Facility facility = facilityMap.get(facilityName);
				if (facility.containsItem(itemNames[i]))
					facilityNames.add(facilityName);
			}
			itemFacilityDTOs[i] = new ItemFacilityDTO(itemNames[i], facilityNames);
		}
		return itemFacilityDTOs;
	}

	public List<List<FacilityNetworkDTO>> getFacilityNetwork() {
		List<List<FacilityNetworkDTO>> lists = new ArrayList<List<FacilityNetworkDTO>>();
		for (String facilityName : facilityMap.keySet()) {
			Map<String, Integer> links = facilityMap.get(facilityName).getLinks();
			List<FacilityNetworkDTO> list = new ArrayList<FacilityNetworkDTO>();
			int speed = facilityMap.get(facilityName).getSpeed();
			int hourPerDay = facilityMap.get(facilityName).getHourPerDay();
			FacilityNetworkDTO fndtoSelf = new FacilityNetworkDTO(facilityName, 0, facilityName, speed, hourPerDay);
			list.add(fndtoSelf);
			for (String facilityNeighbourName : links.keySet()) {
				FacilityNetworkDTO fndtoOthers = new FacilityNetworkDTO(facilityName, links.get(facilityNeighbourName), facilityNeighbourName, speed, hourPerDay);
				list.add(fndtoOthers);
			}
			lists.add(list);
		}
		return lists;
	}
	public List<FacilityOutputDTO> getFacilityOutputByName(String[] facilitiesNames) {

		Arrays.sort(facilitiesNames, new Comparator<String>(){
			public int compare(String c1, String c2) {
				if (c1.charAt(0) < c2.charAt(0))
					return -1;
				else if (c1.charAt(0) > c2.charAt(0))
					return 1;
				else if (c1.charAt(1) < c2.charAt(1))
					return -1;
				else
					return 1;
			}});
		List<FacilityOutputDTO> list = new ArrayList<FacilityOutputDTO>();
		for (String facilityName : facilitiesNames) {
			/*//the following code has been put in getFacilityInfoByFacilityName
			Facility facility = facilityMap.get(facilityName);
			int processingRate = facility.getProcessingRate();
			int processingFeePerDay = facility.getProcessingFeePerDay();
			Map<String, Double> linkTransScheduleMap = new HashMap<String, Double>();
			for (String neighbor : facility.getLinks().keySet()) {
				double transTime = (double)facility.getLinks().get(neighbor) / (double)(facility.getSpeed() * facility.getHourPerDay());
				linkTransScheduleMap.put(neighbor, transTime);
			}
			Map<String, Integer> activieInventoryMap = new HashMap<String, Integer>();
			Map<String, Integer> depletedInventoryMap = new HashMap<String, Integer>();
			for (String itemName : facility.getInventory().keySet()) {
				if (facility.getInventory().get(itemName) == 0)
					depletedInventoryMap.put(itemName, 0);
				else
					activieInventoryMap.put(itemName, facility.getInventory().get(itemName));
			}
			Map<Integer, Integer> scheduleMap = new HashMap<Integer, Integer>();
			for (int day : facility.getSchedule().keySet()) {
				scheduleMap.put(day, facility.getSchedule().get(day));
			}
			FacilityOutputDTO fodto= new FacilityOutputDTO(facilityName, processingRate, processingFeePerDay, linkTransScheduleMap, activieInventoryMap, depletedInventoryMap, scheduleMap);
			*/
			FacilityOutputDTO fodto = getFacilityInfoByFacilityName(facilityName);
			list.add(fodto);
		}
		return list;
	}
	
	public FacilityOutputDTO getFacilityInfoByFacilityName(String facilityName) {
		Facility facility = facilityMap.get(facilityName);
		int processingRate = facility.getProcessingRate();
		int processingFeePerDay = facility.getProcessingFeePerDay();
		Map<String, Double> linkTransScheduleMap = new HashMap<String, Double>();
		for (String neighbor : facility.getLinks().keySet()) {
			double transTime = (double)facility.getLinks().get(neighbor) / (double)(facility.getSpeed() * facility.getHourPerDay());
			linkTransScheduleMap.put(neighbor, transTime);
		}
		Map<String, Integer> activieInventoryMap = new HashMap<String, Integer>();
		Map<String, Integer> depletedInventoryMap = new HashMap<String, Integer>();
		for (String itemName : facility.getInventory().keySet()) {
			if (facility.getInventory().get(itemName) == 0)
				depletedInventoryMap.put(itemName, 0);
			else
				activieInventoryMap.put(itemName, facility.getInventory().get(itemName));
		}
		Map<Integer, Integer> scheduleMap = new HashMap<Integer, Integer>();
		for (int day : facility.getSchedule().keySet()) {
			scheduleMap.put(day, facility.getSchedule().get(day));
		}
		return new FacilityOutputDTO(facilityName, processingRate, processingFeePerDay, linkTransScheduleMap, activieInventoryMap, depletedInventoryMap, scheduleMap);
	}

	public List<FacilityOutputDTO> getAllFacilityOutput() {
		List<String> facilitiesNamesList = new ArrayList<String>();
		String[] facilitiesNames = new String[facilityMap.size()];
		for (String str : facilityMap.keySet())
			facilitiesNamesList.add(str);
		for (int i = 0; i < facilitiesNames.length; i++) {
			facilitiesNames[i] = facilitiesNamesList.get(i);
		}
		return getFacilityOutputByName(facilitiesNames);
	}

	public boolean containsFacility(String facilityName) {
		return facilityMap.containsKey(facilityName);
	}

	public boolean containsItem(String itemId) {
		for (String facilityName : facilityMap.keySet()) {
			Facility f = facilityMap.get(facilityName);
			if (f.getInventory().contains(itemId))
				return true;
		}
		return false;
	}
	public Map<String, Integer> findFacilityInvByItem(String requestFacilityName, String itemId) {
		Map<String, Integer> fInvMap = new HashMap<String, Integer>();
		for (String facilityName : facilityMap.keySet()) {
			Facility f = facilityMap.get(facilityName);
			if (f.getInventory().contains(itemId) && !f.getFacilityName().equals(requestFacilityName))
				fInvMap.put(facilityName, f.getInventory().get(itemId));	
		}
		return fInvMap;
	}
	
	public void updateInventoryByOrder(String facilityName, String itemId, int qtyUsed) {
		Facility facility = facilityMap.get(facilityName);
		facility.reduceInventory(itemId, qtyUsed);
		
	} 
	
	public void updateScheduleByOrder(String facilityName, int qty, int orderStartDay) {
		Facility facility = facilityMap.get(facilityName);
		facility.updateSchedule(orderStartDay, qty);
	}
	/*
	public int getCurrentProcessAbilityByFacility(String facilityName) {
		return facilityMap.get(facilityName).getCurerntProcessingAbility();
	}
	
	public int getEarliestProcessStartDayByFacility(String facilityName) {
		return facilityMap.get(facilityName).getEarliestAvailableDay();
	}
	*/
	public int getProcessingTimeByFacility(String facilityName, int qty, int orderStartDay) {
		return facilityMap.get(facilityName).getProcessingTime(qty, orderStartDay);
	}
}
