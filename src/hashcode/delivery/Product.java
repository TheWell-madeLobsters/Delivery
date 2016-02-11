package hashcode.delivery;

public class Product {

    public int id;
    public int weight;
    
    public Product(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object product) {
        return this.id == ((Product)product).id;
    }
    
    
    
}
