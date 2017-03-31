package dto;

public class InventoryQuantityPairDTO implements Comparable<InventoryQuantityPairDTO>{
	public String itemName;
	public int quantity;
	public InventoryQuantityPairDTO(String itemName, int quantity) {
		//super();
		this.itemName = itemName;
		this.quantity = quantity;
	}
	@Override
	public int compareTo(InventoryQuantityPairDTO o) {
		if (this.itemName.charAt(0) != o.itemName.charAt(0))
			return this.itemName.charAt(0) - o.itemName.charAt(0);
		else
			return this.itemName.charAt(1) - o.itemName.charAt(1);
	}
	
	
	
}
