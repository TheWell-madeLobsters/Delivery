package hashcode.delivery;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    public int warehouseId;
    public int positionX;
    public int positionY;
    
    public Map<Product, Integer> products;
    
    public Warehouse(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.products = new HashMap<>();
    }
    
    public void dropItem(Product product, int quantity) {
        if(products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }
    
    public boolean canPickItem(Product product, int quantity) {
        int loaded = products.get(product);
        int remaining = loaded - quantity;
        if(remaining > 0) {
            return true;
        } else if (remaining == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean pickItem(Product product, int quantity) {
        int loaded = products.get(product);
        int remaining = loaded - quantity;
        if(remaining > 0) {
            products.put(product, remaining);
            return true;
        } else if (remaining == 0) {
            products.remove(product);
            return true;
        } else {
            return false;
        }
    }
    
}
