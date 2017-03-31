package entity;

import java.util.Map;
import java.util.Set;

public class Inventory {
    private Map<String, Integer> invMap;
    
    public Inventory(Map<String, Integer> invMap) {
        this.invMap = invMap;
    }
    public int get(String itemName) {
        return invMap.get(itemName);
    }
    
    public boolean contains(String itemName) {
        return invMap.containsKey(itemName);
    }
    
    public Set<String> keySet() {
    	return invMap.keySet();
    }
    public void reduceInv(String itemId, int qtyUsed) {
    	int qtyUpdated = invMap.get(itemId) - qtyUsed;
    	invMap.put(itemId, qtyUpdated);
    }
}