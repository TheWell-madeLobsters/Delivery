package hashcode.delivery;

public class Unload extends Command{
    public int droneId;
    public int warehouseId;
    public int productType;
    public int quantity;
    
    public Unload(int droneId, int warehouseId, int productType, int quantity) {
        super(CommandType.UNLOAD);
        this.droneId = droneId;
        this.warehouseId = warehouseId;
        this.productType = productType;
        this.quantity = quantity;
    }
    
    public String toString() {
        return this.droneId + " U " + this.warehouseId + " " + productType + " " + quantity;
    }
}
