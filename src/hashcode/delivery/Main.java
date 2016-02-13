/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode.delivery;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco Terrinoni
 */
public class Main {

    private static List<Warehouse> warehouses;
    private static List<Order> orders;
    private static List<Drone> drones;

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
     * Acquire the input file and initialize all the data structures.
     *
     * @param filename
     */
    private static void readInput(String filename) {
        int numDrones;
        int maxPayload;
        Position firstWarehousePosition;
        BufferedReader br = null;

        String currentLine;
        try {
            // First line read
            br = new BufferedReader(new FileReader("input/" + filename + ".in"));
            currentLine = br.readLine();
            String[] basicData = currentLine.split(" "); // split current line by using spaces
            rows = Integer.parseInt(basicData[0]);
            columns = Integer.parseInt(basicData[1]);
            numDrones = Integer.parseInt(basicData[2]);
            turns = Integer.parseInt(basicData[3]);
            maxPayload = Integer.parseInt(basicData[4]);

            // Initialize warehouses
            firstWarehousePosition = new Position();
            // TODO

            // Initialize drones
            drones = new ArrayList<>();
            for (int i = 0; i < numDrones; i++) {
                drones.add(new Drone(i, maxPayload, firstWarehousePosition));
            }

            // Initialize orders
            // TODO
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: input file not found. Exception: " + e.getMessage());
            System.exit(0); // force JVM to terminate
        } catch (IOException e) {
            System.err.println("ERROR: unable to read from file. Exception: " + e.getMessage());
            System.exit(0); // force JVM to terminate
        }

    }

    /**
     * Algorithm.
     *
     * @param filename
     */
    private static void process(String filename) {
    }

    /**
     * Write the list of commands to the output file.
     *
     * @param filename
     */
    private static void writeOutput(String filename) {
    }
}
