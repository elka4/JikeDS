package java2novice.dp.prototype;
 
public class PrototypeTest {
 
    public static void main(String a[]){
        ColorStore.getColor("red").fillColor();
        ColorStore.getColor("green").fillColor();
        ColorStore.getColor("green").fillColor();
        ColorStore.getColor("red").fillColor();
    }
}
