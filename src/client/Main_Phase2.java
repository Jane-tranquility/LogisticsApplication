package client;

import java.util.ArrayList;
import java.util.List;

import dto.FacilityOutputDTO;
import dto.LogisticsRecord;
import dto.LogisticsRecordPairDTO;
import dto.OrderOutputDTO;
import dto.OrderOutputDTO_PS;
import exception.ParameterException;
import service.FacilityService;
import service.OrderProcessorService;

public class Main_Phase2 {
    public static void main(String[] args) throws ParameterException {
        System.out.println("****Facility Status Output and Format for all 18 Facilities****");
        List<FacilityOutputDTO> facilityOutputDTOs = FacilityService.getInstance().getAllFacilityOutput();
        for (FacilityOutputDTO fodto : facilityOutputDTOs)
            fodto.display();
        System.out.println("**** End Facility Status Output ****");
        
        //print order output
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Order Output");
        System.out.println("----------------------------------------------------------------------------------");
        List<LogisticsRecordPairDTO> resultList = OrderProcessorService.getInstance().getOrderOutputRsc();
        List<OrderOutputDTO> oodtoList = OrderProcessorService.getInstance().generateOrderOutputDTO(resultList);
        
        for (int i = 0; i < oodtoList.size(); i++) {
            OrderOutputDTO oodto = oodtoList.get(i);
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("Order #" + (i + 1));
            System.out.println("\tOrder Id: \t" + oodto.orderId);
            System.out.println("\tOrder Time:     " + oodto.startTime);
            System.out.println("\tDestination:    " + oodto.destName);
            System.out.println("\tList of Order Items: ");
            int j = 1;
            for (String itemId : oodto.itemMap_order.keySet()) {
                System.out.println("\t\t" + j + ") Item Id:    " + itemId + ",    Quantity: " + oodto.itemMap_order.get(itemId));
            }
            System.out.println("Processing Solution:");
            System.out.println("\tTotal Cost:\t     $" + oodto.cost);
            System.out.println("\t1st Delivery Day:    " + oodto.firstDivDay);
            System.out.println("\tLastDeliveryDay:     " + oodto.lastDivDay);
            System.out.println("\tOrder Items:");
            System.out.println("\tItemId  Quantity Cost        # resources used  First Day  Last Day");
            for (OrderOutputDTO_PS ps : oodto.psList) {
                System.out.print("\t"+ps.itemId + "  " + ps.qty + " \t $" + ps.cost + " \t" + ps.sused + " \t       \t" + ps.firstDDay +"  \t " + ps.lastDDay);
                if (ps.backOrder != 0)
                    System.out.println(" Back-Order: " + ps.backOrder + "more items are needed");
                else
                    System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------");
        }
        System.out.println("****Facility Status Output and Format for all 18 Facilities****");
        
        for (FacilityOutputDTO fodto : facilityOutputDTOs)
            fodto.display();
        System.out.println("**** End Facility Status Output ****");
    }
}