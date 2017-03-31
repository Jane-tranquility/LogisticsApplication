package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

import dto.FacilityOutputDTO;
import dto.ItemDTO;
import service.FacilityService;
import service.ItemService;
import service.NetworkService;


public class TestNetwork {

	//@Test
	public void test() {
		String f1 = "Santa Fe, NM";
		String f2 = "Chicago, IL";
		String[] fs = new String[1];
		fs[0] = f2;
		List<FacilityOutputDTO> fodtos = FacilityService.getInstance().getFacilityOutputByName(fs);
		for (FacilityOutputDTO fodto : fodtos)
			fodto.display();
		//NetworkService.getInstance().findBestPath(f1, f2).display();
		//fail("Not yet implemented");
		ItemDTO[] idtos = ItemService.getInstance().getItemsOutput();
		Arrays.sort(idtos);
		for (ItemDTO idto : idtos)
			idto.display();
		
		String f11 = "New York City, NY";
		String f12 = "Phoenix, AZ";
		
		
		
		String f21 = "Atlanta, GA";
		String f22 = "St. Louis, MO";
		//NetworkServiceV2.getInstance().findBestPath(f1, f2).display();
		//NetworkService.getInstance().findBestPath(f11, f12).display();
		//NetworkService.getInstance().findBestPath(f21, f22).display();
	}
	//@Test
	public void testNetWork() {
		String f11 = "New York City, NY";
		String f12 = "Phoenix, AZ";
		
		
		
		String f21 = "Atlanta, GA";
		String f22 = "St. Louis, MO";
		//NetworkServiceV2.getInstance().findBestPath(f1, f2).display();
		//NetworkService.getInstance().findBestPath(f11, f12).display();
		//NetworkService.getInstance().findBestPath(f21, f22).display();
	}
	
	@Test
	public void test0(){
		List<String> lowPath = new ArrayList<String>();
		List<String> lowPathLocal = new ArrayList<String>();
		lowPath.add("hello");
		lowPath.add("world");
		
		for (String str : lowPath)
        	lowPathLocal.add(str);
       
        lowPath = new ArrayList<String>();
        System.out.println(lowPathLocal.get(0) + lowPathLocal.get(1));
        System.out.println(lowPath.isEmpty());
	}

}
