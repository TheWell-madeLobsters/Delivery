/*
 * The MIT License
 *
 * Copyright 2016 The Well-made Lobsters.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hashcode.delivery;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class
 *
 * @author Marco Terrinoni
 */
public class Main {

    private static List<Warehouse> warehouses;
    private static List<Order> orders;
    private static List<Drone> drones;
    private static List<Product> products;

    private static int rows;
    private static int columns;
    private static int turns;

    private List<Command> commands;

    private static final String TEST = "test";
    private static final String BUSY_DAY = "busy_day";
    private static final String REDUNDANCY = "redundancy";
    private static final String MOTHER_OF_ALL_WAREHOUSES = "mother_of_all_warehouses";

    public static void main(String[] args) {
        process(TEST);
//        process(BUSY_DAY);
//        process(REDUNDANCY);
//        process(MOTHER_OF_ALL_WAREHOUSES);
    }

    /**
     * The core.
     *
     * @param filename
     */
    private static void process(String filename) {
        readInput(filename);
    }

    /**
     * Acquire the input file and initialize all the data structures.
     *
     * @param filename
     */
    private static void readInput(String filename) {
        int numDrones;
        int maxPayload;
        int numProducts;
        int numWarehouses;
        int numOrders;
        Position firstWarehousePosition;
        BufferedReader br = null;
        String[] basicData;
        String currentLine;

        try {
            // First line read
            br = new BufferedReader(new FileReader("input/" + filename + ".in"));
            currentLine = br.readLine();
            basicData = currentLine.split(" "); // split current line by using spaces
            rows = Integer.parseInt(basicData[0]);
            columns = Integer.parseInt(basicData[1]);
            numDrones = Integer.parseInt(basicData[2]);
            turns = Integer.parseInt(basicData[3]);
            maxPayload = Integer.parseInt(basicData[4]);

            // Second line read
            currentLine = br.readLine();
            numProducts = Integer.parseInt(currentLine);

            // Third line read
            currentLine = br.readLine(); // read weights array
            basicData = currentLine.split(" "); // split current line by using spaces
            products = new ArrayList<>();
            for (int i = 0; i < numProducts; i++) { // products initialization
                Product p = new Product(i, Integer.parseInt(basicData[i]));
                products.add(p);
            }

            // Fourth line read
            currentLine = br.readLine();
            numWarehouses = Integer.parseInt(currentLine);

            // Warehouses initialization
            warehouses = new ArrayList<>();
            for (int i = 0; i < numWarehouses; i++) {
                currentLine = br.readLine(); // read coordinates
                basicData = currentLine.split(" ");
                int coordRow = Integer.parseInt(basicData[0]); // save row
                int coordColumn = Integer.parseInt(basicData[1]); // save column
                Position position = new Position(coordRow, coordColumn); // initialize warehouse position
                currentLine = br.readLine(); // read stored products
                basicData = currentLine.split(" ");
                Map<Integer, Integer> availableProducts = new HashMap<>(); // initialize products array
                for (int j = 0; j < numProducts; j++) { // save products array
                    int value = Integer.valueOf(basicData[j]);
                    if (value != 0) {
                        availableProducts.put(j, value);
                    }
                }
                Warehouse w = new Warehouse(i, position, availableProducts);
                warehouses.add(w); // save current warehouse
            }
            firstWarehousePosition = warehouses.get(0).getPosition(); // save first warehouse position (for drones)

            // Initialize drones
            drones = new ArrayList<>();
            for (int i = 0; i < numDrones; i++) {
                drones.add(new Drone(i, maxPayload, firstWarehousePosition));
            }

            // Fifth line read
            currentLine = br.readLine();
            numOrders = Integer.parseInt(currentLine);

            // Initialize orders
            orders = new ArrayList<>();
            for (int i = 0; i < numOrders; i++) {
                int numProductsOrder;
                currentLine = br.readLine(); // read coordinates
                basicData = currentLine.split(" ");
                int coordRow = Integer.parseInt(basicData[0]); // save row
                int coordColumn = Integer.parseInt(basicData[1]); // save column
                Position position = new Position(coordRow, coordColumn); // initialize order position
                currentLine = br.readLine(); // read num products per order
                numProductsOrder = Integer.valueOf(currentLine);
                Map<Integer, Integer> productsOrder = new HashMap<>();
                currentLine = br.readLine(); // read ordered products
                basicData = currentLine.split(" ");
                for (int j = 0; j < numProductsOrder; j++) {
                    int productId = Integer.valueOf(basicData[j]);
                    if (productsOrder.containsKey(productId)) { // product already saved
                        int currNumProducts = productsOrder.get(productId);
                        currNumProducts++; // update current number of ordered product
                        productsOrder.replace(productId, currNumProducts);
                    } else { // save new product
                        productsOrder.put(productId, 1);
                    }
                }
                Order o = new Order(i, position, numProductsOrder, productsOrder);
                orders.add(o);
            }

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: input file not found. Exception: " + e.getMessage());
            System.exit(0); // force JVM to terminate
        } catch (IOException e) {
            System.err.println("ERROR: unable to read from file. Exception: " + e.getMessage());
            System.exit(0); // force JVM to terminate
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("ERROR: unable to close the file. Exception: " + e.
                            getMessage());
                }
            }
        }
        System.out.println("File read stage completed.");
    }

    /**
     * Write the list of commands to the output file.
     *
     * @param filename
     */
    private static void writeOutput(String filename) {
    }
}
