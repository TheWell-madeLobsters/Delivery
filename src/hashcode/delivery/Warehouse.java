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
    private Map<Product, Integer> availableProducts;

    public Warehouse(int id, Position position, Map<Product, Integer> availableProducts) {
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

    public Map<Product, Integer> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(Map<Product, Integer> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public boolean releaseProduct(Product product, int quantity) {
        if (availableProducts.containsKey(product)) {
            int availableQuantity = availableProducts.get(product);
            if (availableQuantity < 0) {
                return false;
            } else {
                if (availableQuantity == 0) {
                    availableProducts.remove(product);
                } else {
                    availableQuantity -= quantity;
                    availableProducts.replace(product, availableQuantity);
                }
                return true;
            }
        }
        return false;
    }

    public void acquireProduct(Product product, int quantity) {
        if (availableProducts.containsKey(product)) {
            int availableQuantity = availableProducts.get(product);
            availableQuantity += quantity;
            availableProducts.replace(product, availableQuantity);
        } else {
            availableProducts.put(product, quantity);
        }
    }
}
