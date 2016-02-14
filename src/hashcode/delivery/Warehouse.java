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

    public boolean releaseProduct(int productId, int quantity) {
        if (availableProducts.containsKey(productId)) {
            int availableQuantity = availableProducts.get(productId);
            if (availableQuantity < 0) {
                return false;
            } else {
                if (availableQuantity == 0) {
                    availableProducts.remove(productId);
                } else {
                    availableQuantity -= quantity;
                    availableProducts.replace(productId, availableQuantity);
                }
                return true;
            }
        }
        return false;
    }

    public void acquireProduct(int productId, int quantity) {
        if (availableProducts.containsKey(productId)) {
            int availableQuantity = availableProducts.get(productId);
            availableQuantity += quantity;
            availableProducts.replace(productId, availableQuantity);
        } else {
            availableProducts.put(productId, quantity);
        }
    }
}
