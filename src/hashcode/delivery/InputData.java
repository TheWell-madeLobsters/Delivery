package hashcode.delivery;

import java.util.HashMap;
import java.util.Map;

public class InputData {

    public int rows;
    public int columns;
    public int droneNumber;
    public int turnsDeadline;
    public int maximumLoad;
    public int productTypes;
    public int warehousesNumber;
    public int ordersNumber;
    
    public Map<Integer, Product> products;
    public Map<Integer, Warehouse> warehouses;
    public Map<Integer, Drone> drones;
    public Map<Integer, Order> orders;
    
    public InputData() {
        this.products = new HashMap<>();
        this.warehouses = new HashMap<>();
        this.drones = new HashMap<>();
        this.orders = new HashMap<>();
    }
    
}
