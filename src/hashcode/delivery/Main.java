package hashcode.delivery;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String BUSY_DAY = "busy_day";
    public static final String REDUNDANCY = "redundancy";
    public static final String MOTHER_OF_ALL_WAREHOUSES = "mother_of_all_warehouses";

    public static void main(String[] args) {
        //process(BUSY_DAY);
        //process(REDUNDANCY);
        process(MOTHER_OF_ALL_WAREHOUSES);

    }
    
    public static void process(String file) {
        InputData input = readFile(file + ".in");
        
        System.out.println("Warehouse number is " + input.warehousesNumber + " -> " + input.warehouses.size());
        System.out.println("Order number is " + input.ordersNumber + " -> " + input.orders.size());
        System.out.println("Drones number is " + input.droneNumber + " -> " + input.drones.size());
        
        List<Command> commands = new LinkedList<>();
        
        //first step
        //calculate distance between warehouse and orders
        
        Map<Integer, List<Order>> warehousesOrders = new HashMap<>();
        
        for(int i = 0; i < input.warehousesNumber; i++) {
            warehousesOrders.put(i, new LinkedList<>());
        }
        
        for(Order order : input.orders.values()) {
            Warehouse nearest = input.warehouses.get(0);
            double shortestDistance = Utils.calcDistance(nearest.positionX, nearest.positionY, order.positionX, order.positionY);
            for(int i = 1; i < input.warehousesNumber; i++) {
                Warehouse w = input.warehouses.get(i);
                double distance = Utils.calcDistance(w.positionX, w.positionY, order.positionX, order.positionY);
                if(distance < shortestDistance) {
                    nearest = w;
                    shortestDistance = distance;
                }
            }
            warehousesOrders.get(nearest.warehouseId).add(order);
        }
        
        for(Map.Entry<Integer, List<Order>> entry : warehousesOrders.entrySet()) {
            final Warehouse w = input.warehouses.get(entry.getKey());
            Collections.sort(entry.getValue(), new Comparator<Order>() {

                public int compare(Order o1, Order o2) {
                    Integer distance1 = Utils.calcDistance(o1.positionX, o1.positionY, w.positionX, w.positionY);
                    Integer distance2 = Utils.calcDistance(o2.positionX, o2.positionY, w.positionX, w.positionY);
                    return distance1.compareTo(distance2);
                }
                
            });
        }
        
//        Map<Integer, Integer> droneDistancesToDestination = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> orderDelivered = new HashMap<>();
        
        for(Order order : input.orders.values()) {
            Map<Integer, Integer> items = new HashMap<>();
            for(Map.Entry<Product, Integer> orderItem : order.items.entrySet()) {
                items.put(orderItem.getKey().id, orderItem.getValue());
            }
            orderDelivered.put(order.orderId, items);
        }
        
        int currentWarehouseId = 0;
        for(int time = 0; time < 1000; time++) {
            
            for(int droneId = 0; droneId < input.droneNumber; droneId++) {
                System.out.println("Drone #" + droneId + " Turn #" + time);
                
                Drone currentDrone = input.drones.get(droneId);
                Warehouse currentWarehouse = input.warehouses.get(currentWarehouseId);
                List<Order> warehouseOrder = warehousesOrders.get(currentWarehouseId);
                
                for(Order currentOrder : warehouseOrder) {
                    if(!orderDelivered.containsKey(currentOrder.orderId)) {
                        continue;
                    }
                    boolean satisfied = true;
                    for(Map.Entry<Product, Integer> orderItem : currentOrder.items.entrySet()) {
                        Product product = orderItem.getKey();
                        int quantity = orderItem.getValue();
                        if(!currentWarehouse.canPickItem(product, quantity)) {
                            satisfied = false;
                            break;
                        }

                        //int distance = Utils.calcDistance(currentDrone.positionX, currentDrone.positionY, currentOrder.positionX, currentOrder.positionY);

                    }
                    if(satisfied) {
                        System.out.println("Drone #" + droneId + " can satisfy order #" + currentOrder.orderId);
                        
                        for(Map.Entry<Product, Integer> orderItem : currentOrder.items.entrySet()) {
                            Product product = orderItem.getKey();
                            int quantity = orderItem.getValue();
                            
                            int loadQuantity = (currentDrone.maxLoad - currentDrone.getCurrentLoad()) / product.weight;
                            if(loadQuantity > 0) {
                                currentDrone.loadItem(product, loadQuantity);
                                
                                commands.add(new Load(droneId, currentWarehouseId, product.id, loadQuantity));
                                
                                int effectiveQuantity;
                                if(loadQuantity > quantity) {
                                    effectiveQuantity = quantity;
                                } else {
                                    effectiveQuantity = loadQuantity;
                                }
                                   
                                commands.add(new Deliver(droneId, currentOrder.orderId, product.id, effectiveQuantity));
                            }
                        }
                        
                        orderDelivered.remove(currentOrder.orderId);
                        
                    }
                   
                }
                
//                if(!currentDrone.isMoving[time]) {
//                    
//                    if(!currentDrone.isEmpty()) {
                        
//                        for(Order order : warehouseOrders) {
//                            for(Map.Entry<Product, Integer> orderItem : order.items.entrySet()) {
//                                if (orderItem == currentDrone.)
//                                {
//                                }
//                            }
//                        }
//                        
//                        
//                            Product product = orderItem.getKey();
//                            int quantity = orderItem.getValue();
//                            if(currentWarehouse.pickItem(product, orderItem.getValue())) {
//                                int loadQuantity = (currentDrone.maxLoad - currentDrone.getCurrentLoad()) / product.weight;
//                                currentDrone.loadItem(product, loadQuantity);
//                                
//                              
//                                
//                                int effectiveQuantity;
//                                if(loadQuantity > quantity) {
//                                    effectiveQuantity = quantity;
//                                    currentOrder.items.remove(orderItem.getKey());
//                                } else {
//                                    effectiveQuantity = loadQuantity;
//                                    currentOrder.items.put(orderItem.getKey(), quantity - loadQuantity);
//                                }
//                                
//                                commands.add(new Deliver(droneId, currentOrder.orderId, product.id, effectiveQuantity));
//                                
//                                int distance = Utils.calcDistance(currentDrone.positionX, currentDrone.positionY, currentOrder.positionX, currentOrder.positionY);
//                                
//                                currentDrone.isMoving[time] = false;
//                                currentDrone.isMoving[time + distance] = false;
//                            }
//                        }
//                    }
//                    
//                }
                
            }
            
        }
        
        printResult(commands, file + ".out");
    }
    
    public static void printResult(List<Command> commands, String file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(commands.size());
        for (Command instruction : commands) {
            writer.println(instruction);
        }
        writer.close();
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
                
                Order order = new Order(i, Integer.parseInt(orderPosition[0]), Integer.parseInt(orderPosition[1]), itemNumber);
                
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
                Drone drone = new Drone(warehouse0.positionX, warehouse0.positionY, input.maximumLoad, input.turnsDeadline);
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
