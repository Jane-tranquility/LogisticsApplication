package loader;


import java.util.List;


import entity.Order;

public interface OrderLoader {
	public List<Order> loadOrders(String fileName);
}
