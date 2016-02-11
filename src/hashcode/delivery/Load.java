package hashcode.delivery;

public class Load extends Command{
    public int droneId;
    public int warehouseId;
    public int productType;
    public int quantity;
    
    public Load(int droneId, int warehouseId, int productType, int quantity) {
        super(CommandType.LOAD);
        this.droneId = droneId;
        this.warehouseId = warehouseId;
        this.productType = productType;
        this.quantity = quantity;
    }
    
    public String toString() {
        return this.droneId + " L " + this.warehouseId + " " + productType + " " + quantity;
    }
}
