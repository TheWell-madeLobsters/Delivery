/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 The Well-made Lobsters.
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

import java.util.Map;

/**
 * Drones class.
 *
 * @author Marco Terrinoni
 */
public class Drone {

    private int id;
    private int maxWeight;
    private int currentWeight;
    private Position position;
    private Map<Integer, Integer> payload;

    public Drone(int id, int maxWeight, Position position) {
        this.id = id;
        this.maxWeight = maxWeight;
        currentWeight = 0;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Map<Integer, Integer> getPayload() {
        return payload;
    }

    public void setPayload(Map<Integer, Integer> payload) {
        this.payload = payload;
    }

    /**
     * Retrieves the available amount of the specified product.
     *
     * @param productId the id of the specified product
     * @return the number of available specified products
     */
    public int getAvailableQuantityOfProduct(int productId) {
        return payload.get(productId);
    }

    /**
     * Load a certain quantity of the specified product into the drone's payload.
     *
     * @param productId     the id of the specified product
     * @param quantity      the quantity of the specified product
     * @param productWeight the weight of the specified product
     * @return false if the total weight exceeds the maximum payload weight; true otherwise
     */
    public boolean load(int productId, int quantity, int productWeight) {
        System.out.println("Loading " + String.valueOf(quantity) + " items of product " + String.
                valueOf(productId) + " into drone " + String.valueOf(id));
        if (currentWeight + (productWeight * quantity) > maxWeight) { // check maximum weight
            System.err.println("ERROR in drone " + String.valueOf(id)
                    + ": the weight exceed the maximum");
            return false;
        }
        if (payload.containsKey(productId)) { // check if already loaded into drone
            int currQuantity = payload.get(productId);
            currQuantity += quantity;
            payload.replace(productId, currQuantity);
            return true;
        }
        payload.put(productId, quantity); // normal load
        return true;
    }

    /**
     * Unload a certain quantity of the specified product from the drone's payload.
     *
     * @param productId the id of the specified product
     * @param quantity  the quantity of the specified product
     * @return false if there is no instance of the specified product or if the requested quantity
     *         is lower than the available number of products; true otherwise.
     */
    public boolean unload(int productId, int quantity) {
        System.out.println("Unloading " + String.valueOf(quantity) + " items of product " + String.
                valueOf(productId) + " from drone " + String.valueOf(id));
        if (payload.containsKey(productId)) {
            int currQuantity = payload.get(productId);
            if (quantity < currQuantity) {
                currQuantity -= quantity;
                payload.replace(productId, currQuantity);
                return true;
            }
            if (quantity == currQuantity) {
                payload.remove(productId);
                return true;
            }
        }
        System.err.println("ERROR in drone " + String.valueOf(id) + ": the requested product "
                + String.valueOf(productId)
                + " is not in the payload or the quantity is lower than the available");
        return false;
    }

    /**
     * Deliver a certain quantity of the specified product from the drone's payload.
     *
     * @param productId the id of the specified product
     * @param quantity  the quantity of the specified product
     * @return false if there is no instance of the specified product or if the requested quantity
     *         is lower than the available number of products; true otherwise.
     * @see Drone.unload
     */
    public boolean deliver(int productId, int quantity) {
        return unload(productId, quantity);
    }

}
