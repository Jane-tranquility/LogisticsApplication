package dto;

public class ItemDTO implements Comparable<ItemDTO>{
    public String id;
    public int price;
    public ItemDTO(String id, int price) {
        this.id = id;
        this.price = price;
    }
    public void display() {
        /*if (id.length() < 8)
            id = id + " ";*/
        String priceStr = "$" + price;
        System.out.printf("%10s:%8s", id,priceStr);
    }
    @Override
    public int compareTo(ItemDTO o) {
        if (o.id.charAt(0) != id.charAt(0))
            return this.id.charAt(0) - o.id.charAt(0) ;
        else
            return this.id.charAt(1) - o.id.charAt(1);
        
    }
}

