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
public class Order {

    private int id;
    private Position position;
    private int productsNumber;
    private Map<Integer, Integer> products;
    private boolean isFulfilled;

    public Order(int id, Position position, int itemsNumber, Map<Integer, Integer> items) {
        this.id = id;
        this.position = position;
        this.productsNumber = itemsNumber;
        this.products = items;
        isFulfilled = false;
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

    public int getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }

    public void deliverProduct(int productId, int quantity) {
        int currentQuantity = products.get(productId);
        currentQuantity -= quantity;
        productsNumber -= quantity;
        if (currentQuantity == 0) {
            products.remove(productId); // order completed
        } else {
            products.replace(productId, currentQuantity); // update order
        }
        if (productsNumber == 0) { // check if the order is fulfilled
            isFulfilled = true;
        }
    }

    public boolean isIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

}
