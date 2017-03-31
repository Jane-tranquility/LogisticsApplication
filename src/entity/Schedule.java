package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Schedule {
	private Map<Integer, Integer> scheduleMap;

	public Schedule(Map<Integer, Integer> scheduleMap) {
		//super();
		this.scheduleMap = scheduleMap;
	}
	
	public int get(int day) {
		return scheduleMap.get(day);
	}
	public int size() {
		return scheduleMap.size();
	}
	
	public Set<Integer> keySet() {
		return scheduleMap.keySet();
	}
	/*
	public int getRemainingProcessAbility() {
		int count = 0;
		for (int day : scheduleMap.keySet())
			count =count + scheduleMap.get(day);
		return count;
	}
	*/
	
	public int getProcessingTime(int qty, int orderStartDay) {
		int days = orderStartDay;
		for (int k : scheduleMap.keySet()) {
			if (k < orderStartDay)
				continue;
			if (qty <= 0)
				break;
			
			//System.out.print("k: " + k + " ,val: " + scheduleMap.get(k) +", qty" + qty + " ");
			qty = qty - scheduleMap.get(k);
			days++;
		}
		return days - orderStartDay;
	}
	
	
	public void bookSchedule(int orderStartDay, int qty) {
		/*
		List<Integer> listTemp = new ArrayList<Integer>();
		for (int day : scheduleMap.keySet()) {
			if (scheduleMap.get(day) == 0)
				listTemp.add(day);
		}
		Collections.sort(listTemp);
		int day0 = 1;
		if(!listTemp.isEmpty())
			day0 = listTemp.get(listTemp.size() - 1) + 1;
		int day00 = 1;
		*/
		
		/*
		while (day00 < day0) {// update the days unavailable
			scheduleMap.put(day00, 0);
		}
		*/
		
		int day0 = orderStartDay;
		
		while (qty != 0) {
			//if(day0 > 20)
				//break;
			//System.out.println(day0);
			//System.out.println(day0);
			//System.out.println(qty);
			int temp = scheduleMap.get(day0);
			
			int updated = 0;
			if (temp >= qty) {
				updated = temp - qty;
				qty = 0;
			} else {
				updated = 0;
				qty = qty - temp;
			}
			scheduleMap.put(day0, updated);
			day0++;
		}
		/*//for debug purpose
		for (int dayUpdated : scheduleMap.keySet())
			System.out.print("<" + dayUpdated + ", " +scheduleMap.get(dayUpdated) + " > ");
		*/
	}
}
