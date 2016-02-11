package hashcode.delivery;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public int positionX;
    public int positionY;
    public int itemsNumber;
    public Map<Product, Integer> items;
    
    public Order(int positionX, int positionY, int itemsNumber) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.itemsNumber = itemsNumber;
        this.items = new HashMap<>();
    }
    
}
