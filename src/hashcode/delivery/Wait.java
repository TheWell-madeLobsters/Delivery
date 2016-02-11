package hashcode.delivery;

public class Wait extends Command{
    public int droneId;
    public int length;
    
    public Wait(int droneId, int length) {
        super(CommandType.DELIVER);
        this.droneId = droneId;
        this.length = length;
    }
    
    public String toString() {
        return this.droneId + " W " + length;
    }
}
