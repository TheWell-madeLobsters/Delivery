package hashcode.delivery;

public class Deliver extends Command{
    public int droneId;
    public int orderId;
    public int productType;
    public int quantity;
    
    public Deliver(int droneId, int orderId, int productType, int quantity) {
        super(CommandType.DELIVER);
        this.droneId = droneId;
        this.orderId = orderId;
        this.productType = productType;
        this.quantity = quantity;
    }
    
    public String toString() {
        return this.droneId + " D " + this.orderId + " " + productType + " " + quantity;
    }
}
