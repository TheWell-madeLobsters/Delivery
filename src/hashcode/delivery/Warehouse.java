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

import java.util.Map;

/**
 * Warehouses class.
 *
 * @author Marco Terrinoni
 */
public class Warehouse {

    private int id;
    private Position position;
    private Map<Integer, Integer> availableProducts;

    public Warehouse(int id, Position position, Map<Integer, Integer> availableProducts) {
        this.id = id;
        this.position = position;
        this.availableProducts = availableProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Map<Integer, Integer> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(Map<Integer, Integer> availableProducts) {
        this.availableProducts = availableProducts;
    }

    /**
     * Release a certain quantity of the specified product from the warehouse
     *
     * @param productId the id of the specified product
     * @param quantity  the quantity of the specified product
     * @return false if the product isn't available or the available quantity is lower that the
     *         requested one; true otherwise.
     */
    public boolean releaseProduct(int productId, int quantity) {
        System.out.println("Releasing " + String.valueOf(quantity) + " items of product " + String.
                valueOf(productId) + " from warehouse " + String.valueOf(id));
        if (availableProducts.containsKey(productId)) {
            int availableQuantity = availableProducts.get(productId);
            availableQuantity -= quantity;
            if (availableQuantity < 0) {
                System.err.println("ERROR in warehouse " + String.valueOf(id)
                        + ": releasing unavailable quantity of product " + String.valueOf(productId));
                return false;
            }
            if (availableQuantity == 0) {
                availableProducts.remove(productId);
                System.out.println("Product " + String.valueOf(productId)
                        + " is not available anymore in warehouse " + String.valueOf(id));
            } else {
                availableProducts.replace(productId, availableQuantity);
            }
            return true;
        }
        System.err.println("ERROR in warehouse " + String.valueOf(id) + ": product " + String.
                valueOf(productId) + " not available");
        return false;
    }

    /**
     * Acquire a certain quantity of the specified product
     *
     * @param productId the id of the specified product
     * @param quantity  the quantity of the specified product
     */
    public void acquireProduct(int productId, int quantity) {
        System.out.println("Acquiring " + String.valueOf(quantity) + " items of the product "
                + String.valueOf(productId) + " into warehouse " + String.valueOf(id));
        if (availableProducts.containsKey(productId)) {
            int availableQuantity = availableProducts.get(productId);
            availableQuantity += quantity;
            availableProducts.replace(productId, availableQuantity);
        } else {
            availableProducts.put(productId, quantity);
        }
    }
}
