package java2novice.dp.prototype;
 
public class RedColor extends Color{
 
    public RedColor() {
        this.colorName = "RED";
    }
 
    @Override
    void fillColor() {
        System.out.println("filling red color...");
    }
}
