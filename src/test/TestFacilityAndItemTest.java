package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import dto.FacilityNetworkDTO;
import dto.ItemDTO;
import dto.ItemFacilityDTO;
import service.FacilityService;
import service.ItemService;

public class TestFacilityAndItemTest {

	@Test
	public void test() {
		System.out.println("TestFacilityAndItemTest Begins");
		Scanner sc = new Scanner(System.in);
		List<String> itemList;
		String[] itemArray;
		while(true) {
			itemList = new ArrayList<String>();
			
			System.out.println("input items, type quit to finish");
			while (true) {
				String itemName = sc.nextLine();
				if ("quit".equals(itemName))
					break;
				itemList.add(itemName);
			}
			System.out.println("input items to be process");
			itemArray = new String[itemList.size()];
			for (int i = 0; i < itemList.size(); i++) {
				itemArray[i] = itemList.get(i);
				System.out.print(itemArray[i] + " ");
			}
			System.out.println();
			if (!ItemService.getInstance().verifyItems(itemArray)) {
				System.out.println("invalid items");
			} else
				break;
		}
		System.out.println("the price of each item: ");
		System.out.println();
		ItemDTO[] itemDTOs = ItemService.getInstance().getItemsCost(itemArray);
		for (ItemDTO itemDTO : itemDTOs)
			itemDTO.display();
		
		System.out.println();
		System.out.println("print the facilities containing listed items");
		System.out.println();
		ItemFacilityDTO[] ifDTOs = FacilityService.getInstance().getFacilitiesByItemNames(itemArray);
		for (ItemFacilityDTO ifDTO : ifDTOs)
			ifDTO.display();
		
		System.out.println();
		System.out.println("print faclityNetWork");
		System.out.println();
		List<List<FacilityNetworkDTO>> fnDTOs = FacilityService.getInstance().getFacilityNetwork();
		for (List<FacilityNetworkDTO> list : fnDTOs) {
			for (FacilityNetworkDTO fnDTO : list) {
				fnDTO.display();
			}
			System.out.println();
		}
		//fail("Not yet implemented");
	}

}
