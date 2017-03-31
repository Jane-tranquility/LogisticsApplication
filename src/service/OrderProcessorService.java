package service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.FacilityOutputDTO;
import dto.ItemArrivalDetail;
import dto.LogisticsDetail;
import dto.LogisticsRecord;
import dto.LogisticsRecordPairDTO;
import dto.LowPathDTO;
import dto.OrderDTO;
import dto.OrderOutputDTO;
import dto.OrderOutputDTO_PS;
import entity.FacilityRecord;
import entity.Order;
import exception.ParameterException;

public class OrderProcessorService {
	
	
	private volatile static OrderProcessorService instance;
	private OrderProcessorService() {
		
	}
	public static OrderProcessorService getInstance() {
		if (instance == null) {
			synchronized (ItemService.class) {
				if (instance == null) {
					instance = new OrderProcessorService();
				}
			}
		}
		return instance;
	}
	
	public List<FacilityRecord> generateFacilityRecord(String requestFacilityName, String itemId, int requestQty, int orderStartDay) throws ParameterException {
		if (!ItemService.getInstance().verifyItem(itemId))
			throw new InvalidParameterException("invalid item");
		List<FacilityRecord> frList = new ArrayList<FacilityRecord>();
		Map<String, Integer> mapTemp = FacilityService.getInstance().findFacilityInvByItem(requestFacilityName, itemId);
		for (String sourceFacilityName : mapTemp.keySet()) {
			LowPathDTO lpdto = NetworkService.getInstance().findBestPath(requestFacilityName, sourceFacilityName);
			int distance = lpdto.distance;
			FacilityOutputDTO fodto = FacilityService.getInstance().getFacilityInfoByFacilityName(requestFacilityName);
			int invInStock = mapTemp.get(sourceFacilityName);
			//int processAbility = FacilityService.getInstance().getCurrentProcessAbilityByFacility(sourceFacilityName);
			int qtyToBeProcessed = requestQty > invInStock ? invInStock : requestQty;
			/*
			System.out.println();
			System.out.println("qty to be processsed" +qtyToBeProcessed);
			System.out.println();
			 */
			int processingTime = FacilityService.getInstance().getProcessingTimeByFacility(sourceFacilityName, qtyToBeProcessed, orderStartDay);
			//qtyToBeProcessed = Math.min(processAbility, qtyToBeProcessed);
			//int processingTime = (int) Math.ceil((double)qtyToBeProcessed / (double)fodto.processingRate);
			//int processStartDay = FacilityService.getInstance().getEarliestProcessStartDayByFacility(sourceFacilityName);
			//processStartDay = Math.max(processStartDay, orderStartDay);
			/*
			System.out.println();
			System.out.println("PROCESSING TIME" + sourceFacilityName + " | "+ processingTime);
			System.out.println();
			 */
			int processEndDay = orderStartDay - 1 + processingTime;
			int travelTime = (int)Math.ceil((double)distance / (double)(lpdto.speed * lpdto.hourPerDay));
			int arrivalDay =  processEndDay + travelTime;
			frList.add(new FacilityRecord(sourceFacilityName, qtyToBeProcessed, processEndDay, travelTime, arrivalDay));
		}
		return frList;
	}

	public List<LogisticsRecordPairDTO> getOrderOutputRsc() throws ParameterException {
		
		List<LogisticsRecordPairDTO> resultList = new ArrayList<LogisticsRecordPairDTO>();
		List<OrderDTO> orderDTOList = OrderService.getInstance().getOrderDTOList();
		for (OrderDTO order : orderDTOList) {
			Map<String, LogisticsRecord> lrMap = new HashMap<String, LogisticsRecord>();
			//Order order = orderMap.get(rfName);
			for (String itemId : order.keySet()) {
				List<FacilityRecord> solutionList = new ArrayList<FacilityRecord>();
				int requestQty = order.get(itemId);
				int requestQtyupdated = requestQty;
				List<FacilityRecord> frList = generateFacilityRecord(order.getDestName(), itemId, requestQty, order.getStartTime());
				//remove those facilities with no stock
				
				for (int i = 0; i < frList.size(); i++) {
					if (frList.get(i).getQty() == 0)
						frList.remove(i);
				}
				
				Collections.sort(frList, new Comparator<FacilityRecord>() {
					public int compare(FacilityRecord fr1, FacilityRecord fr2) {
						return fr1.getArrivalDay() - fr2.getArrivalDay();		
					}
				});
				
				// end of the step 4
				//while (requestQtyupdated != 0) {
				for (FacilityRecord fr : frList) {
					//System.out.println(fr.getFacilityName()); // for debug
					//System.out.println(itemId); // for debug
					//System.out.println("REQUESTQTYUPDATED" + requestQtyupdated);
					int markQty = fr.getQty();
					//System.out.println(order.getStartTime());
					if (requestQtyupdated < markQty) {
						fr.setQty(requestQtyupdated);
						int updatedProcessingTime = FacilityService.getInstance().getProcessingTimeByFacility(fr.getFacilityName(), fr.getQty(), order.getStartTime());
						int updatedEndProcessingDay = order.getStartTime() - 1 + updatedProcessingTime;
						int updatedArrivalTime = updatedEndProcessingDay + fr.getTravelTime();
						fr.update(updatedEndProcessingDay, updatedArrivalTime);
						FacilityService.getInstance().updateInventoryByOrder(fr.getFacilityName(), itemId, requestQtyupdated);// update the inventory
						//System.out.println(order.getStartTime());
						FacilityService.getInstance().updateScheduleByOrder(fr.getFacilityName(), requestQtyupdated, order.getStartTime());

						requestQtyupdated = 0;
					} else {
						FacilityService.getInstance().updateInventoryByOrder(fr.getFacilityName(), itemId, markQty);// update the inventory
						requestQtyupdated = requestQtyupdated - markQty;
						FacilityService.getInstance().updateScheduleByOrder(fr.getFacilityName(), markQty, order.getStartTime());
					}

					solutionList.add(fr);
					if (requestQtyupdated == 0)
						break;
				}
				//}
				// end of step 5
				Map<String, Integer> frCostMap = new HashMap<String, Integer>();
				int totalCostPerOrderItem = 0;
				List<ItemArrivalDetail> iadList = new ArrayList<ItemArrivalDetail>();
				List<LogisticsDetail> ldList = new ArrayList<LogisticsDetail>();
				double accPct = 0;
				int accNum = 0;
				for (FacilityRecord fr : solutionList) {
					int itemCost = fr.getQty() * ItemService.getInstance().getItemCostByItemId(itemId);
					FacilityOutputDTO fodto = FacilityService.getInstance().getFacilityInfoByFacilityName(fr.getFacilityName());
					int processCost = (fr.getEndDay() - order.getStartTime() + 1) * fodto.processingFeePerDay;
					int shipCost = fr.getTravelTime() * 500; //500 is constant through the project
					int costPerFr = itemCost + processCost + shipCost;
					frCostMap.put(fr.getFacilityName(), costPerFr);
					//System.out.println(costPerFr);
					totalCostPerOrderItem = totalCostPerOrderItem + costPerFr;
					double pct = (double)fr.getQty() / (double)requestQty;
					accPct = accPct + pct;
					accNum = accNum + fr.getQty();
					ItemArrivalDetail iad = new ItemArrivalDetail(fr.getArrivalDay(), fr.getQty(), pct, accPct);
					iadList.add(iad);
					LogisticsDetail ld = new LogisticsDetail(fr.getFacilityName(), fr.getQty(), requestQty, costPerFr, order.getStartTime(), fr.getEndDay(), fr.getEndDay() + 1, fr.getArrivalDay());
					ldList.add(ld);
				}
				int backOrderNum = requestQty - accNum;
				LogisticsRecord lr = new LogisticsRecord(itemId, requestQty, totalCostPerOrderItem, iadList, ldList, backOrderNum);					
				lrMap.put(itemId, lr);
				//lr.display();
				//iadList.clear();
				//ldList.clear();
			}
			LogisticsRecordPairDTO lrpdto = new LogisticsRecordPairDTO(order.getOrderId(), lrMap);
			resultList.add(lrpdto);
		}
		return resultList;
	}
	// for pontential use
	public List<LogisticsRecord> generateLogisticsRecord(List<LogisticsRecordPairDTO> resultList) {
		List<LogisticsRecord> list = new ArrayList<LogisticsRecord>();
		for (LogisticsRecordPairDTO lrpdto : resultList) {
			Map<String, LogisticsRecord> tempMap = lrpdto.lrMap;
			for (String itemId: tempMap.keySet()) {
				list.add(tempMap.get(itemId));
			}
		}
		return list;
	}
	
	public List<OrderOutputDTO> generateOrderOutputDTO(List<LogisticsRecordPairDTO> resultList) {
		List<OrderOutputDTO> oodtoList = new ArrayList<OrderOutputDTO>();
		for (LogisticsRecordPairDTO lrpdto : resultList) {
			String orderId = lrpdto.orderId;
			OrderDTO odto = OrderService.getInstance().getOrderDTOByOrderId(orderId);
			int startTime = odto.startTime;
			String destName = odto.destName;
			Map<String, Integer> itemMap_order = odto.itemMap_order;
			int cost0 = 0;
			int firstDivDay0 = Integer.MAX_VALUE;
			int lastDivDay0 = 0;
			List<OrderOutputDTO_PS> psList = new ArrayList<OrderOutputDTO_PS>();
			Map<String, LogisticsRecord> lrMap = lrpdto.lrMap;
			for (String itemId : lrMap.keySet()) {
				LogisticsRecord lr = lrMap.get(itemId);
				int qty = lr.qty;
				int cost = lr.cost;
				int sused = lr.ldList.size();
				List<LogisticsDetail> ldList = lr.ldList;
				Collections.sort(ldList, new Comparator<LogisticsDetail>() {
					public int compare(LogisticsDetail ld1, LogisticsDetail ld2) {
						return ld1.travelEndDay - ld2.travelEndDay;
					}
				});
				int firstDDay = lr.ldList.get(0).travelEndDay;
				int lastDDay = lr.ldList.get(lr.ldList.size() - 1).travelEndDay;
				int backOrder = lr.backOrderNum;
				OrderOutputDTO_PS ps = new OrderOutputDTO_PS(itemId, qty, cost, sused, firstDDay, lastDDay, backOrder);
				psList.add(ps);
				cost0 = cost0 + cost;
				firstDivDay0 = Math.min(firstDivDay0, firstDDay);
				lastDivDay0 = Math.max(lastDivDay0, lastDDay);
			}
			OrderOutputDTO oodto = new OrderOutputDTO(orderId, startTime, destName, itemMap_order, cost0, firstDivDay0, lastDivDay0, psList);
			oodtoList.add(oodto);
		}
		return oodtoList;
	}

}
