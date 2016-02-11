package hashcode.delivery;

public class Utils {

    public static int calcDistance(int x1, int y1, int x2, int y2) {
        return (int)Math.ceil(Math.sqrt((x2-x1)^2 + (y2-y1)^2));
    }
    
}
