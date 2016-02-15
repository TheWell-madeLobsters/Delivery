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
 * Orders class.
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

    /**
     * Deliver a product for the current order.
     *
     * @param productId the product Id to be delivered
     * @param quantity  the number of products to be delivered
     * @return false if the product is not requested or if the delivered amount or products is
     *         higher than the requested one; true otherwise.
     */
    public boolean deliverProduct(int productId, int quantity) {
        System.out.println("Delivering " + String.valueOf(quantity) + " items of product " + String.
                valueOf(productId) + " for order " + String.valueOf(id));
        if (products.containsKey(productId)) {
            int currentQuantity = products.get(productId);
            if (currentQuantity < quantity) {
                System.err.println("ERROR in order " + String.valueOf(id)
                        + ": delivering a quantity of product " + String.valueOf(productId)
                        + " higher than requested (" + String.valueOf(quantity) + " instead of "
                        + String.valueOf(currentQuantity) + ")");
                return false;
            }

            currentQuantity -= quantity;
            productsNumber -= quantity;
            if (currentQuantity == 0) {
                products.remove(productId); // product order completed
            } else {
                products.replace(productId, currentQuantity); // update order
            }
            if (productsNumber == 0) { // check if the order is completely fulfilled
                isFulfilled = true;
                System.out.println("Order " + String.valueOf(id) + " fulfilled");
            }
            return true;
        }
        System.err.println("ERROR in order " + String.valueOf(id)
                + ": delivering a non requested product " + String.valueOf(productId));
        return false;
    }

    public boolean isIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

}
