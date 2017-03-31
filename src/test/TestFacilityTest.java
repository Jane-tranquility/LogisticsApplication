package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dto.ItemFacilityDTO;
import factory.FacilityLoaderFactory;
import service.FacilityService;

public class TestFacilityTest {

	@Test
	public void test() {
		String[] itemsName = new String[2];
		itemsName[0] = "PU238";
		itemsName[1] = "ABC123";
		ItemFacilityDTO[] itemFacilityDTOs = FacilityService.getInstance().getFacilitiesByItemNames(itemsName);
		for (ItemFacilityDTO i : itemFacilityDTOs)
			i.display();
		fail("Not yet implemented");
	}

}
