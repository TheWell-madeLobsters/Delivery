package hashcode.delivery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static final String BUSY_DAY = "busy_day";
    public static final String REDUNDANCY = "redundancy";
    public static final String MOTHER_OF_ALL_WAREHOUSES = "mother_of_all_warehouses";

    public static void main(String[] args) {
        process(BUSY_DAY);

    }
    
    public static void process(String file) {
        InputData input = readFile(file + ".in");
        
        System.out.println("Warehouse number is " + input.warehousesNumber + " -> " + input.warehouses.size());
        System.out.println("Order number is " + input.ordersNumber + " -> " + input.orders.size());
        System.out.println("Drones number is " + input.droneNumber + " -> " + input.drones.size());
        
    }
    
    public static InputData readFile(String file) {
        BufferedReader br = null;
        
        InputData input = new InputData();
        
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(file));

            sCurrentLine = br.readLine();
            String[] basicData = sCurrentLine.split(" ");
            input.rows = Integer.parseInt(basicData[0]);
            input.columns = Integer.parseInt(basicData[1]);
            input.droneNumber = Integer.parseInt(basicData[2]);
            input.turnsDeadline = Integer.parseInt(basicData[3]);
            input.maximumLoad = Integer.parseInt(basicData[4]);

            sCurrentLine = br.readLine();
            input.productTypes = Integer.parseInt(sCurrentLine);
            
            sCurrentLine = br.readLine();
            String[] productTypes = sCurrentLine.split(" ");
            for(int i = 0; i < productTypes.length; i++) {
                Product product = new Product(i, Integer.parseInt(productTypes[i]));
                input.products.put(i, product);
            }
            
            sCurrentLine = br.readLine();
            input.warehousesNumber = Integer.parseInt(sCurrentLine);
            
            for(int i = 0; i < input.warehousesNumber; i++) {
                sCurrentLine = br.readLine();
                String[] warehousePosition = sCurrentLine.split(" ");
                Warehouse warehouse = new Warehouse(Integer.parseInt(warehousePosition[0]), Integer.parseInt(warehousePosition[1]));
                
                sCurrentLine = br.readLine();
                String[] warehouseItems = sCurrentLine.split(" ");
                for(int j = 0; j < warehouseItems.length; j++) {
                    warehouse.dropItem(input.products.get(j), Integer.parseInt(warehouseItems[j]));
                }
                input.warehouses.put(i, warehouse);
            }
            
            sCurrentLine = br.readLine();
            input.ordersNumber = Integer.parseInt(sCurrentLine);
            
            for(int i = 0; i < input.ordersNumber; i++) {
                sCurrentLine = br.readLine();
                String[] orderPosition = sCurrentLine.split(" ");
                
                sCurrentLine = br.readLine();
                int itemNumber = Integer.parseInt(sCurrentLine);
                
                Order order = new Order(Integer.parseInt(orderPosition[0]), Integer.parseInt(orderPosition[1]), itemNumber);
                
                sCurrentLine = br.readLine();
                String[] orderItems = sCurrentLine.split(" ");
                for(int j = 0; j < orderItems.length; j++) {
                    Product product = input.products.get(Integer.parseInt(orderItems[j]));
                    if(order.items.containsKey(product)) {
                        order.items.put(product, order.items.get(product) + 1);
                    } else {
                        order.items.put(product, 1);
                    }
                }
                input.orders.put(i, order);
            }
            
            Warehouse warehouse0 = input.warehouses.get(0);
            for(int i = 0; i < input.droneNumber; i++) {
                Drone drone = new Drone(warehouse0.positionX, warehouse0.positionY, input.maximumLoad);
                input.drones.put(i, drone);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return input;
    }

}
