package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dto.ItemDTO;
import service.ItemService;

public class TestItemTest {

	@Test
	public void testVerifyItems() {
		String[] itemsName = new String[2];
		itemsName[0] = "PU238";
		itemsName[1] = "ABC123";
		//itemsName[2] = "JBL3100";
		 System.out.println(ItemService.getInstance().verifyItems(itemsName));
		 ItemDTO[] is = ItemService.getInstance().getItemsCost(itemsName);
		 for (ItemDTO i : is)
			 i.display();
		fail("Not yet implemented");
	}

}
