package entity;

public class ItemImpl implements Item{
	private String id;
	private int price;
	
	public ItemImpl(String id, int price) {
		this.id = id;
		this.price = price;
	}
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
