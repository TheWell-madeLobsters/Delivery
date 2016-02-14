/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode.delivery;

import java.util.Map;

/**
 *
 * @author Marco Terrinoni
 */
public class Drone {

    private int id;
    private int maxWeight;
    private Position position;
    private Map<Product, Integer> payload;

    public Drone(int id, int maxWeight, Position position) {
        this.id = id;
        this.maxWeight = maxWeight;
        this.position = position;
    }
    
    // TODO: complete Drone class

}
