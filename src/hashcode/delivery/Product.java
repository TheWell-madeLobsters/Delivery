/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode.delivery;

/**
 *
 * @author Marco Terrinoni
 */
public class Product {

    private int type; // product Id
    private int weight;

    public Product(int type, int weight) {
        this.type = type;
        this.weight = weight;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean equals(Product product) {
        return this.type == product.getType();
    }

}
