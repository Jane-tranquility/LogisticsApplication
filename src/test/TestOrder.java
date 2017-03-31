package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sun.org.apache.xpath.internal.operations.Or;

import dto.FacilityOutputDTO;
import dto.ItemDTO;
import dto.LogisticsRecord;
import dto.LogisticsRecordPairDTO;
import exception.ParameterException;
import service.FacilityService;
import service.ItemService;
import service.NetworkService;
import service.OrderProcessorService;
import service.OrderService;

public class TestOrder {

	//@Test
	public void test() {
		//test updateScheduleByOrder
		
		/*
		FacilityService.getInstance().updateScheduleByOrder("Chicago, IL", 14);
		System.out.println();
		FacilityService.getInstance().updateScheduleByOrder("Chicago, IL", 16);
		System.out.println();
		FacilityService.getInstance().updateScheduleByOrder("Chicago, IL", 34);
		*/
		

	}
	@Test
	public void testOrderProcess() throws ParameterException {
		/*
		List<LogisticsRecordPairDTO> resultList = OrderProcessorService.getInstance().getOrderOutputRsc();
		List<LogisticsRecord> lrList = OrderProcessorService.getInstance().generateLogisticsRecord(resultList);
		for (LogisticsRecord lr : lrList)
			lr.display();
		System.out.println("****Facility Status Output and Format for all 18 Facilities****");
		List<FacilityOutputDTO> facilityOutputDTOs = FacilityService.getInstance().getAllFacilityOutput();
		for (FacilityOutputDTO fodto : facilityOutputDTOs)
			fodto.display();
		System.out.println("**** End Facility Status Output ****");
		*/
		
		NetworkService.getInstance().findBestPath("Miami, FL", "New York City, NY").display();
		NetworkService.getInstance().findBestPath("Miami, FL", "Nashville, TN").display();
		//NetworkService.getInstance().findBestPath("Miami, FL", "Fargo, ND").display();
		//NetworkService.getInstance().findBestPath("Miami, FL", "Denver, CO").display();
		//NetworkService.getInstance().findBestPath("Miami, FL", "Chicago, IL").display();
		//NetworkService.getInstance().findBestPath("Miami, FL", "Seattle, WA");
		
	}

}
