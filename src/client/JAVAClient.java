package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dto.FacilityOutputDTO;
import dto.ItemDTO;
import exception.ParameterException;
import service.FacilityService;
import service.ItemService;
import service.NetworkService;

public class JAVAClient {
	public static void main(String[] args) {
		//Facility Status Output and Format for all 18 Facilities
		System.out.println("****Facility Status Output and Format for all 18 Facilities****");
		List<FacilityOutputDTO> facilityOutputDTOs = FacilityService.getInstance().getAllFacilityOutput();
		for (FacilityOutputDTO fodto : facilityOutputDTOs)
			fodto.display();
		System.out.println("**** End Facility Status Output ****");
		System.out.println();
		System.out.println();
		//Item Catalog Content Output
		System.out.println("****Item Catalog Content Output****");
		System.out.println("Item Catalog:");
		ItemDTO[] idtos = ItemService.getInstance().getItemsOutput();
		Arrays.sort(idtos);
		for (int i = 0; i < idtos.length; i++) {
			idtos[i].display();
			if ((i + 1) % 4 == 0)
				System.out.println();
		}
		System.out.println("****End Item Catalog Output****");
		System.out.println();
		System.out.println();
		System.out.println("**** Shortest Path output results****");
		System.out.println("Shortest Path Tests:");
		String[][] facilityNamesToBeTested = new String[10][2];
		facilityNamesToBeTested[0][0] = "Santa Fe, NM";
		facilityNamesToBeTested[0][1] = "Chicago, IL";

		facilityNamesToBeTested[1][0] = "Atlanta, GA";
		facilityNamesToBeTested[1][1] = "St. Louis, MO";

		facilityNamesToBeTested[2][0] = "Seattle, WA";
		facilityNamesToBeTested[2][1] = "Nashville, TN";

		facilityNamesToBeTested[3][0] = "New York City, NY";
		facilityNamesToBeTested[3][1] = "Phoenix, AZ";

		facilityNamesToBeTested[4][0] = "Fargo, ND";
		facilityNamesToBeTested[4][1] = "Austin, TX";

		facilityNamesToBeTested[5][0] = "Denver, CO";
		facilityNamesToBeTested[5][1] = "Miami, FL";

		facilityNamesToBeTested[6][0] = "Austin, TX";
		facilityNamesToBeTested[6][1] = "Norfolk, VA";

		facilityNamesToBeTested[7][0] = "Miami, FL";
		facilityNamesToBeTested[7][1] = "Seattle, WA";

		facilityNamesToBeTested[8][0] = "Los Angeles, CA";
		facilityNamesToBeTested[8][1] = "Chicago, IL";

		facilityNamesToBeTested[9][0] = "Detroit, MI";
		facilityNamesToBeTested[9][1] = "Nashville, TN";

		for (int i = 0; i < facilityNamesToBeTested.length; i++) {
			try {
				System.out.print((char)('a' + i) + ") ");
				NetworkService.getInstance().findBestPath(facilityNamesToBeTested[i][0], facilityNamesToBeTested[i][1]).display();
			} catch (ParameterException e) {
				e.printStackTrace();
			} 
		}
		System.out.println("**** End Shortest Path output results****");
		
		
	}
}
