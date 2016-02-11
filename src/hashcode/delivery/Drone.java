package hashcode.delivery;

import java.util.HashMap;
import java.util.Map;

public class Drone {

    public int positionX;
    public int positionY;
    public int maxLoad;
    public Map<Product, Integer> inventory;
    
    public boolean[] isMoving;
    
    public Drone(int positionX, int positionY, int maxLoad, int turns) {
        this.positionX = positionX; 
        this.positionY = positionY;
        this.maxLoad = maxLoad;
        this.inventory = new HashMap<>();
        isMoving = new boolean[turns];
        for(int i = 0; i < turns; i++) {
            isMoving[i] = true;
        }
    }
    
    public boolean loadItem(Product product, int quantity) {
        int addedWeight = product.weight * quantity;
        if(getCurrentLoad() + addedWeight <= maxLoad) {
            if(inventory.containsKey(product)) {
                inventory.put(product, inventory.get(product) + quantity);
            } else {
                inventory.put(product, quantity);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isEmpty() {
        return this.inventory.size() == 0;
    }
    
    public boolean unloadItem(Product product, int quantity) {
        int loaded = inventory.get(product);
        int remaining = loaded - quantity;
        if(remaining > 0) {
            inventory.put(product, remaining);
            return true;
        } else if (remaining == 0) {
            inventory.remove(product);
            return true;
        } else {
            return false;
        }
    }
    
    public int getCurrentLoad() {
        int currentLoad = 0;
        for(Map.Entry<Product, Integer> entry : this.inventory.entrySet()) {
            currentLoad += entry.getKey().weight * entry.getValue();
        }
        return currentLoad;
    }
    
}
