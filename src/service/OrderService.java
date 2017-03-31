package service;

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
import dto.LowPathDTO;
import dto.OrderDTO;
import entity.FacilityRecord;
import entity.Order;
import exception.ParameterException;
import factory.OrderLoaderFacotry;
import loader.OrderLoader;



public class OrderService {
	//private List<FacilityRecord> frList = new ArrayList<FacilityRecord>();
	//private Map<String, Order> orderMap = new HashMap<String, Order>();
	private List<Order> orderList = new ArrayList<Order>();
	private static OrderLoader orderLoader = OrderLoaderFacotry.loadOrderLoader("XML");
	private volatile static OrderService instance;
	private OrderService() {
		orderList = orderLoader.loadOrders("orderXML.xml");
	}
	public static OrderService getInstance() {
		if (instance == null) {
			synchronized (ItemService.class) {
				if (instance == null) {
					instance = new OrderService();
				}
			}
		}
		return instance;
	}
	/*
	//this function-getAllOrder is for test purpose
	public void getAllOrder() {
		for (Order order : orderList) {
			System.out.print(order.getOrderId() + ", " + order.getDestName() +", " + order.getStartTime());
			for (String str : order.keySet())
				System.out.print(" " + str + ", " + order.get(str));
			System.out.println();
			System.out.println();
		}
	}
	 */
	public List<OrderDTO> getOrderDTOList() {
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		for (Order order : orderList) {
			String orderId = order.getOrderId();
			int startTime = order.getStartTime();
			String destName = order.getDestName();
			Map<String, Integer> itemMap_order = new HashMap<String, Integer>();
			for (String key : order.keySet()) {
				itemMap_order.put(key, order.get(key));
			}
			OrderDTO odto = new OrderDTO(orderId, startTime, destName, itemMap_order);

			list.add(odto);

		}
		return list;
	}
	
	public OrderDTO getOrderDTOByOrderId(String orderId2) {
		Order order = null;
		for (Order orderTemp : orderList) {
			if (orderTemp.getOrderId().equals(orderId2))
				order = orderTemp;
		}
		String orderId = order.getOrderId();
		int startTime = order.getStartTime();
		String destName = order.getDestName();
		Map<String, Integer> itemMap_order = new HashMap<String, Integer>();
		for (String key : order.keySet()) {
			itemMap_order.put(key, order.get(key));
		}
		return new OrderDTO(orderId, startTime, destName, itemMap_order);
		
	}






}
